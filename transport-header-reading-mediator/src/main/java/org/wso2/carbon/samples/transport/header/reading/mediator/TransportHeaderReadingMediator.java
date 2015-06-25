package org.wso2.carbon.samples.transport.header.reading.mediator;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class TransportHeaderReadingMediator extends AbstractMediator{
	
	private static final Log log = LogFactory.getLog(TransportHeaderReadingMediator.class);

	public boolean mediate(MessageContext messageContext) {
		org.apache.axis2.context.MessageContext axisMC = ((Axis2MessageContext) messageContext).getAxis2MessageContext();
		Map headers = (Map) axisMC.getProperty(org.apache.axis2.context.MessageContext.TRANSPORT_HEADERS);
		String fileName = (String) headers.get("FILE_NAME");
		log.info(fileName);
        return true;
    }
}
