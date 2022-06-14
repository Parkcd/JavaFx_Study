package model;

public class UserData {
	private String name;
	private String id;
	private String password;
	private String permit;
	
	public UserData(String name, String id, String password, String permit) {
		
		this.name = name;
		this.id = id;
		this.password = password;
		this.permit = permit;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPermit() {
		return permit;
	}
	public void setPermit(String permit) {
		this.permit = permit;
	}
}
