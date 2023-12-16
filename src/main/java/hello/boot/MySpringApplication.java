package hello.boot;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class MySpringApplication {
    public static void run(Class configClass, String[] args) throws IOException, LifecycleException {
        System.out.println("MySpringBootApplication.run args = "+ List.of(args));

        // tomcat 설정
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080); // port 지정
        tomcat.setConnector(connector); // tomcat을 port 8080에 연결

        // 스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(configClass); // 설정 파일을 파라미터로 전달받음

        // 스프링 MVC Dispatcher Servlet 생성 + 스프링 컨테이너 연결
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);


        String docBase = Files.createTempDirectory("tomcat-basedir").toString();
        // Dispatcher Servlet 등록
        Context context = tomcat.addContext("", docBase); // tomcat에 사용할 contextPath, docBase 지정
        tomcat.addServlet("", "dispatcher", dispatcherServlet); // tomcat에 Dispatcher Servlet 등록
        context.addServletMappingDecoded("/", "dispatcher"); // 등록한 Dispatcher Servlet의 경로 mapping

        tomcat.start(); // tomcat 시작
    }
}
