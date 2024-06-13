/*
 * 프로그램이 이 클래스에서 집중적으로 진행
 */

import java.util.Scanner;
import java.io.*;
public class Home {
	String UserInfoFile="C:\\Users\\LG\\학교관련자료\\3학년 1학기\\오픈소스SW설계\\과제\\EveryWeather\\EveryWeather\\src\\UserInfoFile.txt";
	Customer c;
	Manager m;
	Scanner sc;
	public void run() {		
		sc=new Scanner(System.in);
		while(true) {
			System.out.print("(시작)1.로그인 2.회원등록 3.종료 :");
			int option=sc.nextInt();
			
			switch(option) {
			case 1:
				Login lg=new Login();
				int authority=lg.run();
				if(authority==0) {
					c=new Customer(lg.getID(),lg.getPSWD());
					readCustomerInfo();
					startActivity();
				}
				else if(authority==1) {
					m=new Manager(lg.getID(),lg.getPSWD());
					manageProgram();//미완
				}
				break;
			case 2:
				Register rg=new Register();
				if(rg.run()) {
					System.out.println("정상적으로 등록되셨습니다.");
					break;
				}
				System.out.println("잘못된 정보입니다.");
				break;
			case 3:
				System.out.println("프로그램 종료...");
				sc.close();
				return;
			default :
				System.out.println("잘못된 입력입니다.");
			}
		}
	}
	private void readCustomerInfo() {
		try {
			sc=new Scanner(new File(UserInfoFile));
			String line;
			while(( line=sc.nextLine())!=null) {
				if(equalID(line)) {
					c.setInfo(line);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("not found UserInfo file");
			e.printStackTrace();
		}
		
		
	}
	private boolean equalID(String line) {
		String ID=c.getID();
		int length=ID.length();
		String tempID=line.split(" ")[0];
		int tempLength=tempID.length();
		if(length!=tempLength)return false;
		for(int i=0;i<length;i++) {
			if(ID.charAt(i)!=line.charAt(i))
				return false;
		}
		return true;
	}
	private void startActivity() {
		sc=new Scanner(System.in);
		while(true) {
			System.out.print("(홈)1.날씨 보기 2.시간 등록 3.메세지 받기 4.로그아웃 :");
			int option=sc.nextInt();
			
			switch(option) {
			case 1:
				WeatherUI wui=new WeatherUI(c.getCity());
				break;
			case 2:
				System.out.print("알람받을 시간을 입력하세요(예시 02:30or19:21):");
				String time=sc.next();
				c.addTime(time);
				break;
			case 3:
				Server sv=new Server(c);
				break;
			case 4:
				System.out.println("로그아웃 하셨습니다.");
				break;
			default :
				System.out.println("잘못된 입력입니다.");
			}
			if(option==4)break;
		}
	}
	private void manageProgram() {
		
	}
}
