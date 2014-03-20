package event;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class LogListener implements ApplicationListener {
	// ApplicationContext会在发布LogEvent事件时通知LogListener
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof LogEvent) {
			// 设定时间
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			format.setLenient(false);
			String currentDate = format.format(new Date());
			System.out.println("输出时间：" + currentDate + " 输出内容："
					+ event.toString());
		}
	}
}