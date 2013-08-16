package demostration.sortedset;

import java.util.Map.Entry;

public class One implements Comparable<One>{
	private int i;
	private String pass;
	private Entry<String,Integer> entry;
	public One(){}
	
	public One(String pass){
		this.pass = pass;
		this.i = 0;
	}
	
	public int getI(){
		return this.i;
	}
	public void setI(int i){
		this.i = i;
	}
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

//	public boolean equals(Object o){
//		if(!(o instanceof One))
//			return false;
//		return this.pass.equals(((One)o).getPass());
//	}
//	
//	public int hashCode(){
//		return this.pass.hashCode();
//	}
	
	@Override
	public int compareTo(One o) {
		return this.entry.getValue().compareTo(o.entry.getValue());
	}


}
