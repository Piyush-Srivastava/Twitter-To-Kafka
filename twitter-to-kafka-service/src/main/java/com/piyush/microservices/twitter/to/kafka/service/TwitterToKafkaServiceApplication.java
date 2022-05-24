package com.piyush.microservices.twitter.to.kafka.service;

import com.piyush.microservices.twitter.to.kafka.service.init.StreamInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.piyush.microservices.twitter.to.kafka.service.runner.StreamRunner;

@SpringBootApplication
@ComponentScan(basePackages = "com.piyush.microservices")
public class TwitterToKafkaServiceApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);

    private final StreamInitializer streamInitializer;
    private final StreamRunner streamRunner;

    public TwitterToKafkaServiceApplication(StreamInitializer streamInitializer,StreamRunner runner){
        this.streamRunner = runner;
        this.streamInitializer = streamInitializer;
    }
    public static void main(String[] args) {

        SpringApplication.run(TwitterToKafkaServiceApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("App run...");
        streamInitializer.init();
        streamRunner.start();
    }
}
