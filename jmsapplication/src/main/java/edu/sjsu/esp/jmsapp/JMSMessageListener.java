package edu.sjsu.esp.jmsapp;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class JMSMessageListener implements MessageListener{

	
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.println("Consumer received message : " + textMessage.getText());
		}catch(JMSException e) {
			e.printStackTrace();
		}
		
	}

}
