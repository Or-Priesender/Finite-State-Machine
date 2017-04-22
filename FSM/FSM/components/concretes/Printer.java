package components.concretes;

import java.io.Serializable;

public class Printer implements Runnable,Serializable {
	
	String toPrint;
	
	public Printer(){
		toPrint="Undefined Printer";
	}
	public Printer(String toPrint){
		this.toPrint = toPrint;
	}
	
	@Override
	public void run() {
		System.out.println(toPrint);

	}
	public String getToPrint() {
		return toPrint;
	}
	public void setToPrint(String toPrint) {
		this.toPrint = toPrint;
	}
	
	

}
