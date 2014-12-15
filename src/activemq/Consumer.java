package activemq;

import javax.jms.Connection;  
import javax.jms.ConnectionFactory;  
import javax.jms.Destination;  
import javax.jms.JMSException;  
import javax.jms.MessageConsumer;  
import javax.jms.Session;  
  
import org.apache.activemq.ActiveMQConnectionFactory;  
  
public class Consumer {  
    private final static String brokerURL = "tcp://192.168.11.146:61616";  
    private static transient ConnectionFactory factory;  
    private transient Connection connection;  
    private transient Session session;  
      
    private String[] queueTitles = new String[]{"QueueOne", "QueueTwo"};  
      
    public Consumer() throws JMSException {  
        factory = new ActiveMQConnectionFactory(brokerURL);  
        connection = factory.createConnection();  
        connection.start();  
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
    }  
  
    public static void main(String[] args) throws JMSException {  
        Consumer consumer = new Consumer();  
        for(String qName : consumer.queueTitles){  
            Destination destination = consumer.getSession().createQueue("Queue." + qName);  
            MessageConsumer msgConsumer = consumer.getSession().createConsumer(destination);  
            msgConsumer.setMessageListener(new Listener());  
        }  
    }
      
    public void close(){  
        try {  
            this.connection.close();  
        } catch (JMSException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public Session getSession() {  
        return session;  
    }  
  
}  