/*
 * 날씨 보기 상태에 진입시 시작되는 클래스. 현재 시간과 현재 날씨를 보여줌
 */

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherUI extends JFrame {
	private static final long serialVersionUID = 1L;
    private JLabel timeLabel;
    private Weather weather;
    private JLabel weatherLabel[];
    private JLabel iconLabel;
    private JLabel cityLabel;
    public WeatherUI(String city) {
        setTitle("Current Weather");
        Container c = getContentPane();

        c.setLayout(null);

        // 시간을 표시할 라벨 생성
        timeLabel = new JLabel();
        timeLabel.setSize(200,15);
        timeLabel.setLocation(5,5);
        weatherLabel=new JLabel[5];
        for(int i=0;i<5;i++) {
        	weatherLabel[i]=new JLabel();
            weatherLabel[i].setSize(300,50);
            weatherLabel[i].setLocation(5,30+i*15);
            c.add(weatherLabel[i]);
        }

        c.add(timeLabel);
        
        weather=new Weather(city);
        updateWeather();
        updateTime(); // 시간 업데이트 메서드 호출

        iconLabel=new JLabel();
        String iconUrl=weather.getIconUrl();
        ImageIcon icon;
		try {
			icon = new ImageIcon(new URL(iconUrl));
	        iconLabel.setIcon(icon);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iconLabel.setSize(50,50);
		iconLabel.setLocation(50,140);
		iconLabel.setBackground(Color.gray); // 배경색 설정
        iconLabel.setOpaque(true);
		c.add(iconLabel);
		
		cityLabel=new JLabel("도시:"+city);
		cityLabel.setSize(100,50);
		cityLabel.setLocation(5,15);
		c.add(cityLabel);
		
        setSize(300, 300);
        setVisible(true);
    }

    private void updateTime() {
        Thread thread = new Thread(() -> {
            while (true) {
                // 현재 시간을 가져와서 포맷팅
                LocalDateTime currentTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedTime = currentTime.format(formatter);

                // 라벨에 시간 설정
                timeLabel.setText("현재 시간: " + formattedTime);
                if(currentTime.getHour()%3==0&&currentTime.getMinute()==0&&currentTime.getSecond()==0) {
                	updateWeather();
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
    private void updateWeather() {
        DecimalFormat df = new DecimalFormat("#.##"); // 소수점 두 자리까지 표현하는 DecimalFormat 객체 생성
        weather.update();
        // 현재 온도 표시
        weatherLabel[0].setText("현재 온도: " + df.format(weather.getTemperature()) + "도");

        // 현재 풍속 표시
        weatherLabel[1].setText("\n현재 풍속 : " + df.format(weather.getWindSpeed()) + "m/s");

        // 최고 온도 표시
        weatherLabel[2].setText("\n최고 온도 : " + df.format(weather.getHighestTemperature()) + "도");

        // 최저 온도 표시
        weatherLabel[3].setText("\n최저 온도 : " + df.format(weather.getLowestTemperature()) + "도");

        // 하늘 상태 표시
        weatherLabel[4].setText("\n하늘 상태 : " + weather.getWeatherDescription());
    }
}

