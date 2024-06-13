/*
 * 메세지 받기 상태에 돌입시 Server클래스에서 시간 체크,메세지 전송을 진행함.
 */

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;

public class Server extends JFrame{
	private static final long serialVersionUID = 1L;
	private Customer c;
	boolean isRunning;
    private JLabel timeLabel;

	public Server() {
		 setTitle("사용자 설정 시간 대기중...");
		 		 
		 Container contentPane = getContentPane();

		 contentPane.setLayout(null);

		 // 시간을 표시할 라벨 생성
		 timeLabel = new JLabel();
		 timeLabel.setSize(200,15);
		 timeLabel.setLocation(5,5);
		 contentPane.add(timeLabel);
		 
		 JLabel standLabel=new JLabel("대기 화면...");
		 standLabel.setSize(300,15);
		 standLabel.setLocation(5, 50);
		 contentPane.add(standLabel);
		 
		 JLabel exitLabel=new JLabel("창을 닫으면 홈으로 이동합니다...");
		 exitLabel.setSize(300,15);
		 exitLabel.setLocation(5, 150);
		 contentPane.add(exitLabel);
		 
		 isRunning=true;
		 
		 updateTime();
		 
		 addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent e) {
		            int option = JOptionPane.showConfirmDialog(Server.this, "정말로 종료하시겠습니까?", "종료 확인", JOptionPane.YES_NO_OPTION);
		            if (option == JOptionPane.YES_OPTION) {
		                dispose(); // 프레임 닫기
		                isRunning=false;
		            }
		            else {
		                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 창을 닫지 않도록 설정
		            }
		        }
		    });
		 setSize(300, 300);
	     setVisible(true);
	}
	public Server(Customer c) {
		this();
		this.c=c;
	}

	private void updateTime() {
        Thread thread = new Thread(() -> {
        	System.out.println("사용자가 설정한 시간 대기 중...");
            while (isRunning) {
                // 현재 시간을 가져와서 포맷팅

                LocalDateTime currentTime = LocalDateTime.now();
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");

                String formattedTime1 = currentTime.format(formatter1);
                String formattedTime2 = currentTime.format(formatter2);
                
                // 라벨에 시간 설정
                timeLabel.setText("현재 시간: "+formattedTime2);

                if(currentTime.getSecond()==0) {
                	if(checkCustomerSetedTime(formattedTime1)) {
                		MessageDisplayUI mdui=new MessageDisplayUI(c.getCity());
                		mdui.run();
                	}
                }
                try {
                    // 1초마다 업데이트
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    
    }
	private boolean checkCustomerSetedTime(String currentTime) {
		ArrayList<String> timelist=(ArrayList<String>) c.vals();
		for(String time:timelist) {
			if(time.equals(currentTime)) {
				return true;
			}
		}
    	return false;
    }
    
}
