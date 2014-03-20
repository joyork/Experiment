package event;

import org.springframework.context.ApplicationEvent;

public class LogEvent extends ApplicationEvent {
	private String level;
	private int type;
	private int subId;
	private String information;
	
	public LogEvent(Object msg){
		super(msg);
	}
	public LogEvent(Object msg,String level,int type,int subId,String information) {
		super(msg);
		this.level = level;
		this.type = type;
		this.subId = subId;
		this.information = information;
			
	}
}