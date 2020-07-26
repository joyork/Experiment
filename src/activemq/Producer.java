package activemq;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;



public class Producer {  
    
      
    /** 
     * 定义连接地址，TCP协议，IP为：192.168.11.146，端口：61616  baiyawen-2
     */  
    private final static String brokerURL = "tcp://192.168.11.146:61616";  
      
    /** 
     * 定义连接工厂 
     */  
    private static transient ConnectionFactory factory;  
      
    /** 
     * 定义连接 
     */  
    private transient Connection connection;  
      
    /** 
     * 定义Session 
     */  
    private transient Session session;  
      
    /** 
     * 定义Producer 
     */  
    private transient MessageProducer producer;  
    private String queueTitle = "queue.stationlog";
    private String[] queueTitles = new String[]{"QueueOne", "QueueTwo"};  
    private static int index = 10;  
      
    /** 
     * 初始化上述的几个属性 
     * @throws JMSException 
     */  
    public Producer() throws JMSException {  
        factory = new ActiveMQConnectionFactory(brokerURL);  
        connection = factory.createConnection();  
        connection.start();  
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
        producer = session.createProducer(null);  
    }  
      
  
    public static void main(String[] args) throws JMSException {  
        Producer producer = new Producer();  
        int i = 0;  
        while(i < 10){  
            producer.sendMsg(i);  
            i++;  
        }  
        producer.close();  
    }  
      
    public void close(){  
        try {  
            this.connection.close();  
        } catch (JMSException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public void sendMsg(int serial) throws JMSException {  
//        int idx = 0;  
//        while (true) {  
//            idx = (int) Math.round(queueTitles.length * Math.random());  
//            if (idx < queueTitles.length) {  
//                break;  
//            }  
//        }  
//        String title = queueTitles[idx];
//        Destination destination = session.createQueue("Queue." + title);  
    	String title = queueTitle;
        Destination destination = session.createQueue(title);  

        for(int i = 0; i < index; i++){  
            Message message = session.createObjectMessage(title + ", Serial" + serial + ", index" + i);  
            System.out.println("Sending: id: " + ((ObjectMessage) message).getObject() + " on queue: " + destination);  
            producer.send(destination, message);  
        }  
    }  
      
  
}  