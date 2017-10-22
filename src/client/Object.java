package client;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Object implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String seqNumber;
	private String timestamp;
	public Object(int seqNumber1, Date timestamp) {
		super();
		this.seqNumber = "NumeroSecuencia="+Integer.toString(seqNumber1)+"";
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String reportDate = df.format(timestamp);

		this.timestamp = "marcaTiempo=" + reportDate;
	}
	public String getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber ="NumeroSecuencia="+seqNumber;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String reportDate = df.format(timestamp);

		this.timestamp = "marcaTiempo=" + reportDate;
	}
	
	
}
