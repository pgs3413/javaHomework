# javaHomework
java大作业
网站地址：http://8.131.93.221/trading
(二)	架构及关键技术说明
本系统采用完全的前后端分离技术，摒弃了jsp等前后端耦合的框架。前端采用html，css，javascript开发界面，使用jQuery框架和ajax技术向后端请求json数据或普通字符串技术。后端采用spring容器融合了springMVC框架和mybatis plus框架，即SSM框架，架构设计为MVC架构。数据库为MySql数据库，采用了以数据为中心的软件体系结构，模块与模块之间完全分离，通过数据库进行消息传递。
(三)	项目结构介绍
1、项目结构：
1.1包结构：
1.1.1 org.pan.bean包：存放与数据库的表相应的pojo类以及多表映射的类，这些类用于当从数据库获取数据后转换成Java对象进行业务逻辑处理，最后转换成json格式返回给前端
1.1.2 org.pan.controller包：此包为控制层，存放了springMVC控制类，采用注解式开发，AdminController类为控制管理员模块相应的页面跳转和处理数据请求， 	BusinessController类为控制商家模块相应的页面跳转和处理数据请求，	GoodsController类为处理商品模块的数据请求，如搜索商品，具体商品信息， 		具体商品类别等，UserController类为控制用户模块相应的页面跳转和处理数据		请求，JumpController类为控制各模块登陆与注册的相关页面跳装和数据请求，
采用多级路径结构划分请求。
1.1.3 org.pan.service包：该包存放各模块的service层类，采用JDK动态代理技术和Spring AOP技术为该层的类生成动态代理类，所以所有的service类都有相应的接口，实现类则实现相应的业务逻辑处理，向上提供接口供controller类调用。
1.1.4 org.pan.mapper包：该包存放各模块的dao层类和xml文件，向下对数据库进行操作获得相应数据，向上提供接口供service类调用。采用mybatis框架开发DAO		层，所以在此包下开发接口与相应的xml文件。
1.1.5 org.pan.filter包：此包存放过滤器类，过滤一些非法请求，配置一些全局的配置，在没有登陆的情况下无法直接访问个人和后台数据。
      1.2 配置资源目录 resources：
1.2.1 db.properties:数据库配置文件，配置数据库驱动，数据库URL，账号，密码
1.2.2 log4j.properties:日志配置文件，配置了日志文件路径，格式等属性
1.2.3 mybatis.xml:mybatis配置文件，配置别名等
1.2.4 springMVC.xml:springMVC配置文件，配置注解驱动和注解扫描器
1.2.5 spring.xml:主要配置文件，采用Spring JDBC数据源配置数据源，注入mybatis	相关的FactoryBean类，使mybatis集成进Spring框架，注入MapperScannerConfigurer类扫描Mapper包，配置组件扫描器，注入事务管理类，配置事务通知，配置aop事务管理service类。
      1.3 静态资源目录 wepapp：
1.3.1 WEB-INF下的web.xml文件：tomcat启动后读取的第一个文件，故在此配置一些全局配置，如：注册DispatcherServlet类，此类为SpringMVC的中央调度器，传入springMVC文件路径供调度器读取，创造Spring容器，所以要设置load-startup为1，并映射相应的请求的路径；配置字符编码过滤器，解决中文乱码问题；注册filter包下的过滤器类，并映射相应的请求路径；配置错误页面，当发生404或500相应的错误时给予相应的反馈。
       1.3.2 其他资源为html文件，css文件，js文件，字体文件，图片资源等
2、其他特色说明
     	2.1 采用maven项目管理工具开发，开发更简洁，更高效
2.2 采用了矢量图标开发小图片
2.3采用了apache开发的fileupload工具进行从前端上传文件到后端服务器
2.4前端数据请求采用ajax等异步请求技术，使数据响应速度更快，对使用者体验更加友好

(四)	使用说明
1、	项目部署安装
本系统已经部署到阿里云服务器上，可通过互联网直接访问，采用linux操作系统，tomcat网络服务器，mysql数据库部署安装。
访问地址：http://8.131.93.221/trading/
管理员地址：http://8.131.93.221/trading/admin.do
