package com.keshava.shorten;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Properties;

import static com.keshava.shorten.constants.Constants.*;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoApplication.class);

        Arguments arguments = new Arguments(args);
        File conf = arguments.getConfigFile();
        Config config = ConfigFactory.parseFile(conf);

        Properties properties = new Properties();
        properties.put(SERVER_PORT, config.getString(SERVER_PORT));
        properties.put(SERVER_URL, config.getString(SERVER_URL));
        properties.put(DATASOURCE_URL, config.getString(DATASOURCE_URL));
        properties.put(DATASOURCE_USERNAME, config.getString(DATASOURCE_USERNAME));
        properties.put(DATASOURCE_PASSWORD, config.getString(DATASOURCE_PASSWORD));
        properties.put(DRIVER_CLASS_NAME, config.getString(DRIVER_CLASS_NAME));
        application.setDefaultProperties(properties);

        application.run(args);
    }

}
