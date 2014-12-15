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
        //获取temp DB；如果默认没有创建，mongodb会自动创建
        db = mg.getDB("temp");
        //获取users DBCollection；如果默认没有创建，mongodb会自动创建
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
     * <b>function:</b> 查询所有数据
     * @author hoojo
     * @createDate 2011-6-2 下午03:22:40
     */
    private void queryAll() {
        print("查询users的所有数据：");
        //db游标
        DBCursor cur = users.find();
        while (cur.hasNext()) {
            print(cur.next());
        }
    }
     
    @Test
    public void add() {
        //先查询所有数据
        queryAll();
        print("count: " + users.count());
        
        DBObject user = new BasicDBObject();
        user.put("name", "hoojo");
        user.put("age", 24);
        //users.save(user)保存，getN()获取影响行数
        //print(users.save(user).getN());
        
        //扩展字段，随意添加字段，不影响现有数据
        user.put("sex", "男");
        print(users.save(user).getN());
        
        //添加多条数据，传递Array对象
        print(users.insert(new BasicDBObject("name", "tom")).getN());
        
        //添加List集合
        List<DBObject> list = new ArrayList<DBObject>();
        user.put("age", 22);
        list.add(user);
        DBObject user2 = new BasicDBObject("name", "lucy");
        list.add(user2);
        //添加List集合
//        print(users.insert(list).getN());
        print(users.insert(user).getN());
        print(users.insert(user2).getN());
        
        //查询下数据，看看是否添加成功
        print("count: " + users.count());
        queryAll();
    }
     
}