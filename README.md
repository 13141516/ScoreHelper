#ScoreHelper，针对中科院大学成绩查询，当有新成绩出现时，提供邮件提醒服务<br />

#配置
1.数据库的配置：数据库的配置文件在c3p0.xml中，数据库表名为customer,表结构请参考<br />
![image](https://github.com/13141516/ScoreHelper/raw/master/CarPro/screenshot/three.png)<br />
2.邮箱发送账号：邮箱发送账号和密码的设置在score.py文件中<br />
3.python版本及依赖库：python3.4.4<br />
依赖包beautifulsoup，requests 安装：<br />
pip install beautifulsoup4<br />
pip install requests<br />
4.java版本：java version "1.8.0_60"<br />
5.项目打war包后，可直接放置于tomcat直接运行<br />

#使用流程
在浏览器中输入ip:8080/CarPro/注册用户信息即可，如图：<br />
![image](https://github.com/13141516/ScoreHelper/raw/master/CarPro/screenshot/one.png)<br />

#效果图
![image](https://github.com/13141516/ScoreHelper/raw/master/CarPro/screenshot/four.png)<br />
