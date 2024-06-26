package com.onesty.services.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan("com.onesty")
public class Application {

//	@Bean
//	public OpenAPI getOpenApiDocumentation() {
//		return new OpenAPI().info(new Info().title("test"));
//	}

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        String mongoDbHost = ctx.getEnvironment().getProperty("spring.data.mongodb.host");
        String mongoDbPort = ctx.getEnvironment().getProperty("spring.data.mongodb.port");
        String kafkaServers = ctx.getEnvironment().getProperty("spring.kafka.bootstrap-servers");
        log.info("Connected to MongoDb: {}:{}", mongoDbHost, mongoDbPort);
        log.info("Connected to Kafka: {}", kafkaServers);
    }

}
