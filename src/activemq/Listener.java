package activemq;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class Listener implements MessageListener {  
    @Override
    public void onMessage(Message message) {  
        try {  
            System.out.println("Consumer:" + ((ObjectMessage)message).getObject());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }

  
}  