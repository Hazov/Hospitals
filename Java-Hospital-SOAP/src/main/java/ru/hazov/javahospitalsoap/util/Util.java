package ru.hazov.javahospitalsoap.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class Util {

    DatatypeFactory datatypeFactory;

    @Autowired
    Util(DatatypeFactory datatypeFactory) {
        this.datatypeFactory = datatypeFactory;
    }

    public XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
        if(date == null) return null;
        GregorianCalendar gregorianCalendar = toGregorianCalendar(date);
        return datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
    }

    public static GregorianCalendar toGregorianCalendar(Date date){
        if(date == null) return null;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        return gc;
    }
}
