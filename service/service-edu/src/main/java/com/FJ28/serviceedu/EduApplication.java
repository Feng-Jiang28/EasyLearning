package com.FJ28.serviceedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = "com.FJ28") // This is used to change the rule of Component Scanning,
// If this one is not used, Component of the module will be scanned, but if we have a basePackage the base package will be scanned.
// So as the config in the basePackage.
public class EduApplication {

    public static void main(String[] args){
        SpringApplication.run(EduApplication.class, args);

    }
}
