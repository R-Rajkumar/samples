package org.wso2.carbon.samples.message.builder.handler;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.rest.AbstractHandler;
import org.apache.synapse.transport.passthru.util.RelayUtils;

public class MessageBuilderHandler extends AbstractHandler{
	
	private static final Log log = LogFactory.getLog(MessageBuilderHandler.class);

	public boolean handleRequest(MessageContext messageContext) {
		return true;
	}

	public boolean handleResponse(MessageContext messageContext) {
		try {
			RelayUtils.buildMessage(((Axis2MessageContext) messageContext).getAxis2MessageContext());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		// read the body
		log.info(messageContext.getEnvelope().getBody());
		return true;
	}

}
