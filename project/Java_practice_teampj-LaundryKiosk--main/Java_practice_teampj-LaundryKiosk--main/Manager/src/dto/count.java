package dto;

public class count {
	private int cnum;
	private String cname;
	public count(int cnum, String cname) {
		super();
		this.cnum = cnum;
		this.cname = cname;
	}
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
