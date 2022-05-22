## :blush:1、还原数据库

payment_demo.sql，执⾏以下命令还原数据库

`mysql -uroot -p < xxx\payment_demo.sql`

## :smile:2、运⾏后端项⽬

⽤idea打开payment-demo，确认maven仓库的位置，修改`application.yml`中的数据库连接配置，运⾏项⽬

## :smiley: 3、运⾏前端项⽬
安装node.js，如果你希望⽅便的查看和修改前端代码，可以安装⼀个VSCode和相关插件，⽤VSCode打开前端项⽬payment-demo-front，运⾏前端项⽬

**先进入到前端项目目录**

```bash
npm install
npm run serve
```



