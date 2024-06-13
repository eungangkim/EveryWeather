/*
 * 관리자의 정보를 저장
 * 미완성
 */
public class Manager {
	String id;
	String passwd;
	String authority;//어떤 타입으로 할지 미정
	public Manager() {
		authority="1";
	}
	public Manager(String ID,String PSWD) {
		this();
		id=ID;
		passwd=PSWD;
	}
}
