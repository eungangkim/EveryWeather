/*
 * 날씨에 대한 메세지를 생성하기 위한 클래스
 */

public class AnalysisWeather {
	Weather weather;
	public AnalysisWeather() {
		
	}
	public AnalysisWeather(String city) {
		weather=new Weather(city);
	}

	public String analysis() {

		StringBuilder message = new StringBuilder();
		message.append(weather.getCityMessage());
		message.append(weather.getTemperatureMeesage());
		message.append(weather.getHumidityMessage());
		message.append(weather.getWindSpeedMeesage());
		message.append(weather.getWeatherDescriptionMessage());
		return message.toString(); // Stringmessage에 쓰여진 문자열 반환 
	}

}
