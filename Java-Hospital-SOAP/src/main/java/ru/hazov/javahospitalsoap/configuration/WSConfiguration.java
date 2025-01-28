package ru.hazov.javahospitalsoap.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.endpoint.adapter.DefaultMethodEndpointAdapter;
import org.springframework.ws.soap.SoapMessageFactory;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import ru.hazov.javahospitalsoap.jaxb_model.Covid19PatientsReport;
import ru.hazov.javahospitalsoap.jaxb_model.GetPatientsRequest;
import ru.hazov.javahospitalsoap.jaxb_model.GetPatientsResponse;

import java.util.Properties;

@EnableWs
@Configuration
public class WSConfiguration extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "patients")
    DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema patientSchema) {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
        wsdlDefinition.setSchema(patientSchema);
        wsdlDefinition.setPortTypeName("PatientPort");
        wsdlDefinition.setLocationUri("/Patients");
        Properties soapActions = new Properties();
        soapActions.setProperty("getPatients", "getPatients");
        wsdlDefinition.setSoapActions(soapActions);

        return wsdlDefinition;
    }

    @Bean
    public XsdSchema patientSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/getPatients.xsd"));
    }


    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setClassesToBeBound(GetPatientsRequest.class, GetPatientsResponse.class, Covid19PatientsReport.class);
        return marshaller;
    }

    @Bean
    DefaultMethodEndpointAdapter defaultAdapter() {
        return new DefaultMethodEndpointAdapter();
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(SoapMessageFactory messageFactory) {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(messageFactory);
        webServiceTemplate.setMessageFactory(messageFactory);
        webServiceTemplate.setMarshaller(jaxb2Marshaller());
        return webServiceTemplate;
    }

    @Bean
    public SoapMessageFactory messageFactory() {
        SaajSoapMessageFactory factory = new SaajSoapMessageFactory();
        factory.setSoapVersion(SoapVersion.SOAP_11);
        return factory;
    }


}
