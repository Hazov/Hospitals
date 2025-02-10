package ru.hazov;

import org.apache.axiom.om.*;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axiom.om.util.XPathEvaluator;
import org.apache.synapse.MessageContext;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.eip.splitter.IterateMediator;
import org.apache.axis2.context.OperationContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.stream.XMLStreamException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CollectToOperationScopeMediator extends IterateMediator {


    private static final Log log = LogFactory.getLog(CollectToOperationScopeMediator.class);

    public boolean mediate(MessageContext context) {
        OperationContext operationContext = ((Axis2MessageContext) context).getAxis2MessageContext().getOperationContext();
        try {
            Object currentCompensationUrl = context.getProperty("currentCompensationUrl");
            Object currentCompensationPayloadTemplate = context.getProperty("currentCompensationPayloadTemplate");
            if (currentCompensationUrl != null && currentCompensationPayloadTemplate != null) {
                String payload = applyExressions(((Axis2MessageContext) context).getAxis2MessageContext().getEnvelope().toString(), currentCompensationPayloadTemplate.toString());
                OMElement rootElement = getOrCreateRoot(context, operationContext);
                OMElement compensation = createCompensation(rootElement.getOMFactory(), currentCompensationUrl.toString(), payload);
                rootElement.addChild(compensation);
                operationContext.setProperty("compensations", rootElement.toString());
            }
        } catch (Exception e) {
            log.error(e);
            return false;
        }
        return true;
    }

    private OMElement createCompensation(OMFactory factory, String currentCompensationUrl, String payload) throws XMLStreamException {
        OMElement compensationElement = factory.createOMElement("compensation", "mns", "mns");
        OMElement urlElement = factory.createOMElement("url", "mns", "mns");
        factory.createOMText(urlElement, currentCompensationUrl);
        OMElement payloadElementWrapper = factory.createOMElement("payload", "mns", "mns");
        OMElement payloadElement = AXIOMUtil.stringToOM(payload);
        payloadElementWrapper.addChild(payloadElement);
        compensationElement.addChild(urlElement);
        compensationElement.addChild(payloadElementWrapper);
        return compensationElement;
    }

    private OMElement getOrCreateRoot(MessageContext context, OperationContext operationContext) throws XMLStreamException {
        OMElement rootElement;
        Object compensationUrls = operationContext.getProperty("compensations");
        if (compensationUrls == null) {
            OMFactory factory = OMAbstractFactory.getOMFactory();
            OMNamespace ns1 = factory.createOMNamespace("mns", "mns");
            rootElement = factory.createOMElement("compensations", ns1);
        } else {
            rootElement = AXIOMUtil.stringToOM(compensationUrls.toString());
        }
        return rootElement;
    }

    private String applyExressions(String currentPayload, String template) throws Exception {
        Pattern pattern = Pattern.compile("\\$\\{.*}");
        Matcher matcher = pattern.matcher(template);
        while (matcher.find()) {
            template = template.replace(matcher.group(), applyExpression(currentPayload, matcher.group()));
        }
        return template;
    }

    private String applyExpression(String currentPayload, String dollarExpression) throws Exception {
        String expression = dollarExpression.substring(2, dollarExpression.length() - 1);
        XPathEvaluator xPathEvaluator = new XPathEvaluator();
        List<OMElement> list = xPathEvaluator.evaluateXpath(expression, AXIOMUtil.stringToOM(currentPayload), "http://ws.apache.org/ns/synapse");
        return list.get(0).getText();
    }
}
