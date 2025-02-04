package ru.hazov;

import org.apache.axiom.om.*;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.synapse.MessageContext;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.eip.splitter.IterateMediator;
import org.apache.axis2.context.OperationContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.stream.XMLStreamException;

public class CollectToOperationScopeMediator extends IterateMediator {
    String itemProperty;
    String collectionProperty;

    private static final Log log = LogFactory.getLog(CollectToOperationScopeMediator.class);

    public boolean mediate(MessageContext context) {
        OperationContext operationContext = ((Axis2MessageContext) context).getAxis2MessageContext().getOperationContext();
        try {
            OMElement rootElement = getOrCreateCompenstionUrls(context, operationContext);
            Object currentUrl = context.getProperty(itemProperty);
            if (currentUrl != null) {
                OMFactory factory = rootElement.getOMFactory();
                OMElement elt1 = factory.createOMElement(itemProperty, "mns", "mns");
                factory.createOMText(elt1, (String) currentUrl);
                rootElement.addChild(elt1);
                operationContext.setProperty(collectionProperty, rootElement.toString());
            }
        } catch (Exception e) {
            log.error(e);
            return false;
        }
        return true;
    }

    private OMElement getOrCreateCompenstionUrls(MessageContext context, OperationContext operationContext) throws XMLStreamException {
        OMElement rootElement;
        Object compensationUrls = operationContext.getProperty("compensationUrls");
        if (compensationUrls == null) {
            OMFactory factory = OMAbstractFactory.getOMFactory();
            OMNamespace ns1 = factory.createOMNamespace("mns", "mns");
            rootElement = factory.createOMElement(collectionProperty, ns1);
        } else {
            rootElement = AXIOMUtil.stringToOM(compensationUrls.toString());
        }
        return rootElement;
    }

    public String getItemProperty() {
        return itemProperty;
    }

    public void setItemProperty(String itemProperty) {
        this.itemProperty = itemProperty;
    }

    public String getCollectionProperty() {
        return collectionProperty;
    }

    public void setCollectionProperty(String collectionProperty) {
        this.collectionProperty = collectionProperty;
    }
}