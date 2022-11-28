package bean;

import java.io.Serializable;

public class GakuseiDataBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String huri;
	
	public void setId(int id) {
		this.id =id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setHuri(String huri) {
		this.huri = huri;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getHuri() {
		return this.huri;
	}

}