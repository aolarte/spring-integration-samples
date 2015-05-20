package com.javaprocess.examples.integration.main;

import com.javaprocess.examples.integration.impl.App;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andres.olarte on 5/4/15.
 */
public class Main {

    public static void main(String... args) {
        List<String> argList= Arrays.asList(args);
        boolean clientMode=false;
        Set<String> configFiles=new HashSet<String>();
        if (argList.contains("direct")) {
            configFiles.add("spring-int.xml");
            clientMode=true;
        }
        if (argList.contains("client")) {
            configFiles.add("spring-int-client.xml");
            configFiles.add("broker.xml");
            clientMode=true;
        }
        if (argList.contains("server")) {
            configFiles.add("spring-int-server.xml");
            configFiles.add("broker.xml");
        }
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext(configFiles.toArray(new String[1]));

        if (clientMode) {
            System.out.println("Running in client mode");
            App app = context.getBean(App.class);
            app.run();

        } else {
            System.out.println("Running in server mode");
            try {
                Thread.sleep(1000000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Closing context");
        context.close();


    }

}
