package org.demo.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.demo.mapper.DynamicSQLMapper;
import org.demo.po.Employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Shawn
 * @version 1.0
 * @description: TODO
 * @date 2022/2/17 19:53
 */
public class MybatisTest {

    public static void main(String[] args) throws IOException {
        // 加载mybatis框架主配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 读取解析配置文件内容，创建SqlSessionFacory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        // 执行数据库操作
        Employee employee = new Employee();
        employee.setId(1);
        List<Employee> list = mapper.selectEmployeeBySql();
        System.out.println(list);
        // List<Employee> list = mapper.getEmp1();
        // Department department1 = mapper.getDeptStep(1);
        // List<Employee> list = mapper.getEmp1();
        // System.out.println(department1);
        sqlSession.commit();
        sqlSession.close();
    }


}
