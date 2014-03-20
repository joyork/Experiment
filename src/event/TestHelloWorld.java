package event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestHelloWorld {
	public static void main(String[] args) {
		ApplicationContext actx = new FileSystemXmlApplicationContext(
				"resources/config.xml");
		Log log = (Log) actx.getBean("log");
		log.Log("gf");
	}
}