package main;

import config.AppCtx;
import config.AppCtxWithPrototype;
import java.io.IOException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import spring.Client;

public class MainWithPrototype {

    public static void main(String[] args) throws IOException {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(
            AppCtxWithPrototype.class
        );

        Client client = ctx.getBean(Client.class);
        client.send();

        Client client1 = ctx.getBean("client", Client.class);
        Client client2 = ctx.getBean("client", Client.class);
        System.out.println("client1 == client2 : " + (client1 == client2));

        ctx.close();
    }
}
