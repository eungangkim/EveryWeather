/*
 * 로그인시에 로그인 정보를 받고 올바른 정보인지 확인하는 클래스
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Login {
	private String LoginInfoFile;
	private String ID;
	private String PSWD;
	Scanner sc;
	public Login() {
		LoginInfoFile="C:\\Users\\LG\\학교관련자료\\3학년 1학기\\오픈소스SW설계\\과제\\EveryWeather\\EveryWeather\\src\\LoginInfoFile.txt";
	}
	public int run(){
		requestLoginInfo();
		return LoginCheck();

	}
	private void requestLoginInfo() {
		sc=new Scanner(System.in);
		
		System.out.print("id:");
		ID=sc.next();
		System.out.print("passwd:");
		PSWD=sc.next();
	}
	private int LoginCheck() {

		try {
			sc=new Scanner(new File(LoginInfoFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sc.useDelimiter("[ \n]");
		while (sc.hasNext()) {
			String tempID=sc.next();
			String tempPSWD=sc.next();
			String authority=sc.next();
			if(tempID.equals(ID)) {
				if(tempPSWD.equals(PSWD)) {
					if(authority.equals("0")) {
						sc.close();
						System.out.println("로그인되었습니다.");
						return 0;
					}
					else {
						System.out.println("관리자 모드입니다.");
						return 1;
					}
                }
				else {
					System.out.println("잘못된 ID혹은 PSWD입니다.");
					sc.close();
					return -1;
                }
			}
		}
		System.out.println("잘못된 ID혹은 PSWD입니다.");
		sc.close();
		return -1;
	}
	public String getID() {return ID;}
	public String getPSWD() {return PSWD;}
}
