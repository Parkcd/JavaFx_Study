package model;

public class Team01 {
		//3개 필드변수
	private String fullname;
	private String password;
	private String email;
	private String host;
	public Team01(String fullname, String password, String email, String host) {

		this.fullname = fullname;
		this.password = password;
		this.email = email;
		this.host = host;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
}
