package com.example.filesegment.service;

import com.example.filesegment.bean.SegmentFile;
import com.example.filesegment.mapper.SegmentFileMapper;
import com.example.filesegment.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 分片存储
// 文件存在确认
// 文件整合
@Service
@Slf4j
public class SegmentFileService {

    @Value("${file.temp}")
    private String tempFileDir;


    private final SegmentFileMapper segmentFileMapper;

    public SegmentFileService(SegmentFileMapper segmentFileMapper) {
        this.segmentFileMapper = segmentFileMapper;
    }

    /**
     * 该文件存在，返回数据
    */
    public SegmentFile checkSegmentFile(String key){
        List<SegmentFile> segmentFiles = segmentFileMapper.getSegmentFileByKey(key);
        if(segmentFiles!=null&&segmentFiles.size()>0) {
            return segmentFiles.get(0);
        } else {
            return null;
        }
    }
    /**
     * 第一次出现的文件，把数据存到数据库中
     * savePath为文件夹绝对位置
    */
    public boolean createSegmentFile(String originFileName, String savePath, long size, int segmentSize, String key){
        String saveFileName = FileUtil.createSaveFileName(key, originFileName);
        SegmentFile segmentFile = new SegmentFile();
        // filepath为完整路径
        segmentFile.setFilePath(savePath + saveFileName);
        segmentFile.setFileName(saveFileName);
        segmentFile.setSize(size);
        segmentFile.setSegmentIndex(0);
        segmentFile.setSegmentSize(segmentSize);

        int total = (int) (size / segmentSize);
        if(size % segmentSize != 0) {
            total++;
        }
        segmentFile.setSegmentTotal(total);
        segmentFile.setMd5Key(key);

        return segmentFileMapper.insertSegmentFile(segmentFile) > 0;
    }

    /**
     * 存储分片到服务器
    */
    public boolean saveSegment(MultipartFile file,String key, String originFileName, int segmentIndex){
        String saveFileName = FileUtil.createSaveFileName(key, originFileName);
        String segmentFileName = FileUtil.getSegmentName(saveFileName, segmentIndex);
        // 存储分片，方便之后使用
        boolean saveSuccess = upload(file,  tempFileDir +segmentFileName);
        if(saveSuccess){
            // 修改数据库中分片记录
            SegmentFile segmentFile = segmentFileMapper.getSegmentFileByKey(key).get(0);
            segmentFile.setSegmentIndex(segmentFile.getSegmentIndex()+1);
            int row = segmentFileMapper.updateSegmentFile(segmentFile);
            return row > 0;
        }else {
            return false;
        }
    }

    /**
     * 将所有的分片联合成同一文件
    */
    public boolean mergeSegment(String key) {
        SegmentFile segmentFile = segmentFileMapper.getSegmentFileByKey(key).get(0);
        int segmentCount = segmentFile.getSegmentTotal();
        FileInputStream fileInputStream = null;
        FileOutputStream outputStream = null;
        byte[] byt = new byte[10 * 1024 * 1024];
        try {
            // 整合结果文件
            File newFile = new File(segmentFile.getFilePath());
            outputStream = new FileOutputStream(newFile, true);
            int len;
            for (int i = 0; i < segmentCount; i++) {
                String segmentFilePath = FileUtil.getSegmentName(tempFileDir + segmentFile.getFileName(), i + 1);
                fileInputStream = new FileInputStream(segmentFilePath);
                while ((len = fileInputStream.read(byt)) != -1) {
                    outputStream.write(byt, 0, len);
                }
            }
        } catch (IOException e) {
            log.error("分片合并异常,异常原因：",e);
            return false;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                log.info("IO流正常关闭");
            } catch (Exception e) {
                log.error("IO流关闭异常,异常原因：",e);
            }
        }
        log.info("分片合并成功");
        return true;
    }

    /**
     * 完成合并，删除分片文件
    */
    public void deleteSegments(String key) throws InterruptedException {
        // 为了保证不被占用，先回收数据流对象
        System.gc();
        Thread.sleep(1000);
        SegmentFile segmentFile = segmentFileMapper.getSegmentFileByKey(key).get(0);
        int segmentCount = segmentFile.getSegmentTotal();
        List<String> remain = new ArrayList<>();
        int finished = 0;
        int[] visited = new int[segmentCount];
        for (int i = 0; i < segmentCount; i++) {
            String segmentFilePath = FileUtil.getSegmentName(tempFileDir + segmentFile.getFileName(), i + 1);
            remain.add(segmentFilePath);
            File file = new File(segmentFilePath);
            boolean result = file.delete();
            if(result) {
                finished++;
                visited[i] = 1;
            }
            log.info("分片文件: {} 删除 {}" , segmentFilePath, result?"成功":"失败");
        }
        // visited数组，然后完成了再去除，知道count到达总数;二次确认删除
        while(finished<segmentCount){
            System.gc();
            Thread.sleep(1000);
            for(int i=0;i<segmentCount;i++){
                if(visited[i]==0){
                    String segmentFilePath = FileUtil.getSegmentName(segmentFile.getFilePath(), i + 1);
                    remain.add(segmentFilePath);
                    File file = new File(segmentFilePath);
                    boolean result = file.delete();
                    if(result){
                        visited[i] = 1;
                        finished++;
                    }
                    log.info("分片文件: {} 删除 {}" , segmentFilePath, result?"成功":"失败");
                }
            }
        }
    }

    /**
     * 存储方法
    */
    private boolean upload(MultipartFile file, String path){
        File dest = new File(path);
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            boolean b = dest.getParentFile().mkdir();
            if(!b){
                return false;
            }
        }
        //保存文件
        try {
            file.transferTo(dest);
            return true;
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
