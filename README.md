thriftDemo
==========

thrift 个人总结文档及相关源码

项目使用gradle构建  
gradle官网：http://www.gradle.org/

文档目录


```
目录	11.	thrift介绍	22.	thrift安装	21.1.	OS X Setup	21.2.	windows	33.	简单实例	33.1.	建立login.thrift文件	33.2.	生成代码	33.3.	建立项目环境	33.4.	实现UserService接口	53.5.	实现服务器端SimpleServerDemo	63.6.	实现客户端SimpleClientDemo	73.7.	测试运行	84.	基础知识	84.1.	数据类型	84.2.	thrift架构	104.3.	基本概念	114.3.1.	Protocol支持的传输格式	114.3.2.	Transport支持的数据传输方式	124.3.3.	Server支持的服务模型	124.4.	服务端编码基本步骤	124.4.1.	实现服务处理接口impl	134.4.2.	创建TProcessor	134.4.3.	创建TServerTransport	134.4.4.	创建TProtocol	144.4.5.	创建TServer	144.4.6.	启动Server	174.5.	客户端编码基本步骤	184.5.1.	创建Transport	184.5.2.	创建TProtocol	184.5.3.	基于TTransport和TProtocol创建 Client	184.5.4.	调用Client的相应方法	185.	问题	185.1.	如何选择服务TServer类型	18TSimpleServer	19TNonblockingServer vs. THsHaServer	19THsHaServer vs. TThreadedSelectorServer	20TThreadedSelectorServer vs. TThreadPoolServer	215.2.	如何实现服务接口的版本变更	236.	参考资料	24
```