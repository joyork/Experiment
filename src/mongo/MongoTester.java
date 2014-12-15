package mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoTester {
    
    private Mongo mg = null;
    private DB db;
    private DBCollection users;
    
    @Before
    public void init() {
        try {
            mg = new Mongo();
            //mg = new Mongo("localhost", 27017);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
        //��ȡtemp DB�����Ĭ��û�д�����mongodb���Զ�����
        db = mg.getDB("temp");
        //��ȡusers DBCollection�����Ĭ��û�д�����mongodb���Զ�����
        users = db.getCollection("users");
    }
    
    @After
    public void destory() {
    	users.drop();
        if (mg != null)
            mg.close();
        mg = null;
        db = null;
        users = null;
        System.gc();
    }
    
    public void print(Object o) {
        System.out.println(o);
    }
    
    /**
     * <b>function:</b> ��ѯ��������
     * @author hoojo
     * @createDate 2011-6-2 ����03:22:40
     */
    private void queryAll() {
        print("��ѯusers���������ݣ�");
        //db�α�
        DBCursor cur = users.find();
        while (cur.hasNext()) {
            print(cur.next());
        }
    }
     
    @Test
    public void add() {
        //�Ȳ�ѯ��������
        queryAll();
        print("count: " + users.count());
        
        DBObject user = new BasicDBObject();
        user.put("name", "hoojo");
        user.put("age", 24);
        //users.save(user)���棬getN()��ȡӰ������
        //print(users.save(user).getN());
        
        //��չ�ֶΣ���������ֶΣ���Ӱ����������
        user.put("sex", "��");
        print(users.save(user).getN());
        
        //��Ӷ������ݣ�����Array����
        print(users.insert(new BasicDBObject("name", "tom")).getN());
        
        //���List����
        List<DBObject> list = new ArrayList<DBObject>();
        user.put("age", 22);
        list.add(user);
        DBObject user2 = new BasicDBObject("name", "lucy");
        list.add(user2);
        //���List����
//        print(users.insert(list).getN());
        print(users.insert(user).getN());
        print(users.insert(user2).getN());
        
        //��ѯ�����ݣ������Ƿ���ӳɹ�
        print("count: " + users.count());
        queryAll();
    }
     
}