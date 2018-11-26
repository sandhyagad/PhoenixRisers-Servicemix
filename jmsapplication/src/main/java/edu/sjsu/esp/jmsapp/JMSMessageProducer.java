package edu.sjsu.esp.jmsapp;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

//import org.apache.activemq.broker.Connection;

public class JMSMessageProducer implements Runnable {

	public void run() {
		
		try {
			//create connection factory from JNDI
			Context context = new InitialContext();
			ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
			
			//create the connection
			Connection connection = connectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			//create the destination as topic
			Destination destination = (Destination) context.lookup("Topic");
			
			//create MessageProducer from the Session to the topic
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			//create messages
			String text = "Hello World! From: " + Thread.currentThread().getName() + ":" + this.hashCode();
			TextMessage message = session.createTextMessage(text);
			
			//Establish connection to consumer on message listener
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new JMSMessageListener());
			
			//Tell the producer to send the message
			System.out.println("Producer sent message: " + message.hashCode() + ":" + Thread.currentThread().getName());
			producer.send(message);
			
			//clean up
			session.close();
			connection.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
