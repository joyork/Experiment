package event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Log implements ApplicationContextAware {
	// 设定变量applicationContext
	private ApplicationContext applicationContext;

	// 变量applicationContext的set方法
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	// 通过publishEvent发布事件
	public int Log(String log) {
		LogEvent event = new LogEvent(log);
		this.applicationContext.publishEvent(event);
		return 0;
	}
}