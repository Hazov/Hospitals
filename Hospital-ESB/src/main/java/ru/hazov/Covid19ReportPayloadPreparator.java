package ru.hazov;

import org.apache.synapse.MessageContext; 
import org.apache.synapse.mediators.AbstractMediator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
    
public class Covid19ReportPayloadPreparator extends AbstractMediator { 
    private static final Log log = LogFactory.getLog(Covid19ReportPayloadPreparator.class);

    public boolean mediate(MessageContext context) {


        log.info("Туточки нахуй");
        log.info("context");
        log.info(context);
        log.info("getContextEntries");
        log.info(context.getContextEntries());
        log.info("getEnvelope");
        log.info(context.getEnvelope());
        return true;
    }
}
