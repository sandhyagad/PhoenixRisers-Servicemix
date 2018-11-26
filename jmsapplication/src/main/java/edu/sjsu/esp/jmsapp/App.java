package edu.sjsu.esp.jmsapp;

public class App {

	public static void main(String []args) throws Exception {
		{
			for(int i = 0; i < 50; i++) {
				thread(new JMSMessageProducer(), false);
				thread(new JMSMessageProducer(), false);
				thread(new JMSMessageProducer(), false);
				thread(new JMSMessageProducer(), false);
				thread(new JMSMessageProducer(), false);
				Thread.sleep(1000);
				
			}
		}
		
	}

	private static void thread(Runnable runnable, boolean daemon){
		Thread brokerThread = new Thread(runnable);
		brokerThread.setDaemon(daemon);
		brokerThread.start();
		
	}
	
}
