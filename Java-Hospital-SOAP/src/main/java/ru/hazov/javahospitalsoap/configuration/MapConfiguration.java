package ru.hazov.javahospitalsoap.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

@Configuration
public class MapConfiguration {
    @Bean
    DatatypeFactory datatypeFactory() throws DatatypeConfigurationException {
        return DatatypeFactory.newInstance();
    }
}
