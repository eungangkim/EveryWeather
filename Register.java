/*
 * 회원 등록시에 회원정보를 입력받고 파일에 저장함.
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Register {
	private String LoginInfoFile;
	private String UserInfoFile;
	Scanner sc;
	
	private String id;
	private String passwd;
	private String name;
	private String tel;
	private String email;
	private String city;
	private String authority;
	public Register() {
		LoginInfoFile="C:\\Users\\LG\\학교관련자료\\3학년 1학기\\오픈소스SW설계\\과제\\EveryWeather\\EveryWeather\\src\\LoginInfoFile.txt";
		UserInfoFile="C:\\Users\\LG\\학교관련자료\\3학년 1학기\\오픈소스SW설계\\과제\\EveryWeather\\EveryWeather\\src\\UserInfoFile.txt";
	}
	public boolean run() {
		requestUserInfo();
		registerUserLoginInfo();
		registerUserInfo();
		return true;
	}
	private void requestUserInfo() {
		sc=new Scanner(System.in);
		
		System.out.print("id를 입력해주세요:");
		id=sc.next();
		System.out.print("passwd를 입력해주세요:");
		passwd=sc.next();
		System.out.print("이름을 입력해주세요:");
		name=sc.next();
		System.out.print("전화번호를 입력해주세요(예시 01012345678):");
		tel=sc.next();
		System.out.print("email을 입력해주세요:");
		email=sc.next();
		System.out.print("지역을 입력해주세요(에시 Daegu Seoul):");
		city=sc.next();
		authority="0";
	}
	private void registerUserLoginInfo() {
		try {
            // FileWriter 객체 생성
            FileWriter writer = new FileWriter(LoginInfoFile,true);
            
            // 파일에 쓸 텍스트
            
            // 파일에 텍스트 쓰기
            writer.write(id+" "+passwd+" "+authority+'\n');

            // FileWriter 종료
            writer.close();
     	  } catch (IOException e) {
            System.out.println("파일에 텍스트를 쓰는 도중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
	}
	private void registerUserInfo() {
		try {
            // FileWriter 객체 생성
            FileWriter writer = new FileWriter(UserInfoFile,true);
            
            // 파일에 쓸 텍스트
            
            // 파일에 텍스트 쓰기
            writer.write(id+" "+passwd+" "+name+" "+tel+" "+email+" "+city+" "+authority+'\n');

            // FileWriter 종료
            writer.close();
     	  } catch (IOException e) {
            System.out.println("파일에 텍스트를 쓰는 도중 오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }
	}
}
