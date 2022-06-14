package home;

import java.net.Socket;

//사용자의 개인 정보를 저장하는 클래스
public class MyInfo {
	
	public static String my_name;
	public static String my_id;
	public static String my_phone;
	public static boolean socketConnect = false;
	public static Socket socket;
	public static String onePostNum;
	
	public static void setName(String names) {
		my_name = names;
	}
	
	public static void setID(String ids) {
		my_id = ids;
	}
	
	public static void setPhone(String phones) {
		my_phone = phones;
	}
	
	public static void setConnect(boolean state) {
		socketConnect = state;
	}
	
	public static void setOnePostNum(String num) {
		onePostNum = num;
	}
	
	public String getName() {
		return my_name;
	}
	
	public String getId() {
		return my_id;
	}
	
	public String getPhone() {
		return my_phone;
	}
	
	public static void setSocket(Socket s) {
		socket = s;
	}
}
