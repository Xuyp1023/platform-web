# 平台交互接口工程 -- @Controller
> 描述：该工程用于编写平台与前端交互的相关接口代码

# 1、源码路径命名规范
### com.betterjr.modules.{module name}

## 如：客户管理模块
### com.betterjr.modules.customer

# 2、platform-dubbo-consumer-springmvc.xml配置
### dubbo:annotation package="com.betterjr.modules.{module name}",多个{module name}用逗号","隔开

# 3、spring-context-platform-dubbo-consumer.xml配置
### context:component-scan base-package="com.betterjr.modules.{module name}",多个{module name}用分号";"隔开

# 4、spring-mvc.xml配置
### context:component-scan base-package="com.betterjr.modules.{module name}",多个{module name}用分号";"隔开