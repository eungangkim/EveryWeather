/*
 * 고객의 정보를 저장
 */
public class Customer {
	private String id;
	private String passwd;
	private String name;
	private String tel;
	private String email;
	private String city;
	private String authority;
	private TimeSet setedTimeList;
	public Customer() {
		setedTimeList=new TimeSet();
		authority="0";
	}
	public Customer(String ID,String PSWD) {
		this();
		id=ID;
		passwd=PSWD;
	}
	public String getID() {return id;}
	public String getName() {return name;}
	public String getTel() {return tel;}
	public String getEmail() {return email;}
	public String getCity() {return city;}
	public String authority() {return authority;}
	public void setID(String i) {id=i;}
	public void setName(String n) { name=n;}
	public void setTel(String t) { tel=t;}
	public void setEmail(String e) { email=e;}
	public void suthority(String a) {authority=a;}
	public void setInfo(String line) {
		String [] info=line.split(" ");
		id=info[0];
		passwd=info[1];
		name=info[2];
		tel=info[3];
		email=info[4];
		if(info[5].length()<3)
			city="Seoul";
		else city=info[5];
		authority=info[6];
	}
	public void addTime(String time) {
		setedTimeList.addTime(time);
	}
	public Iterable<String> vals(){
		return setedTimeList.vals();
	}
}
