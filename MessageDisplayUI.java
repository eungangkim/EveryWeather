/*
 * 서버에서 메세지 전송을 할때 이 클래스를 이용하여 메세지 전송을 진행함.
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
/*
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
*/

public class MessageDisplayUI {
	String message;
	private String city;
	public MessageDisplayUI(String city) {
		this.city=city;
	}
	public void run() {
		makeMessage();
		displayMessage();
	}
	private void makeMessage() {
		AnalysisWeather aw=new AnalysisWeather(city);
    	message=aw.analysis();
	}
	public void displayMessage() {
    	SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Message");
            JLabel label = new JLabel(message);
            frame.add(label);
            frame.pack();
            frame.setVisible(true);
        });
    }
	/*
	private void sendMessageByEmail() {
		String host = "smtp.gmail.com";
	    int port = 587;
	    String username = "your.email@gmail.com";
	    String password = "yourpassword";
	
	    // 이메일 속성 설정
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", port);
	
	    // 세션 생성
	    Session session = Session.getInstance(props, new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    });
	
	    try {
	        // 메시지 작성
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("recipient@example.com"));
	        message.setSubject("JavaMail API를 사용한 이메일 테스트");
	        message.setText("이메일 내용을 입력하세요.");
	
	        // 메시지 전송
	        Transport.send(message);
	
	        System.out.println("이메일이 성공적으로 전송되었습니다.");
	    } catch (MessagingException e) {
	        System.out.println("이메일을 전송하는 도중 오류가 발생했습니다: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	*/
}
