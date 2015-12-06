package startcaft_log4j.log4j_study.core;

import org.apache.log4j.Logger;

/*
 * Log4j是Apache的一个开源项目，通过使用Log4j，我们可以控制日志信息输出的目的地【控制台，文件，GUI组件，甚至是套接口服务器，NT的事件记录器等】；
 * 我们也可以控制每一条日志的输出格式；
 * 通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成代码；
 * 最令人感兴趣的就是，这些只需要通过一个配置文件来灵活地进行配置，二不需要修改程序代码；
 * 
 * 此外，通过Log4j其他语言借口，还可以在C，C++，.Net，PL/SQL程序中使用Log4j，其语法和用法与在Java程序中一样，
 * 使得多语言分布式系统得到一个同意一直的日志组件模块，而且，通过使用各种第三方扩展，可以很方便地讲Log4j集成到J2EE,JINI甚至是SNMP应用中。
 */
public class HelloLog4j {
	
    public static void main( String[] args ){
    	
    	Logger logger = Logger.getLogger(HelloLog4j.class);
    	
    	logger.info("startcaft");
    }
}
