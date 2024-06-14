/*
 * 현재의 날씨정보를 저장하고 관리함
 */


import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;



public class Weather {
	private String currentWeatherUrl;
	private String city="default";
	private String currentWeatherDescription; // 현재 날씨 설명
	private double currentTemperature; // 현재 온도
	private double maxTemperature; // 최고 기온
	private double minTemperature; // 최저 기온
	private int currentHumidity;
	private double currentWindSpeed; // 현재 풍속
	String iconUrl;
	
	public Weather(String city) {
		this.city=city;
		API();
	}
	public void update() {
		API();
	}
    public double getTemperature() {
        return currentTemperature; // 현재 온도를 double로 반환합니다.
    }

    public double getHighestTemperature() {
        return maxTemperature; // 최고 기온을 double로 반환합니다.
    }

    public double getLowestTemperature() {
        return minTemperature; // 최저 기온을 double로 반환합니다.
    }
    public int getHumidity() {
    	return currentHumidity;
    }
    public double getWindSpeed() {
        return currentWindSpeed; // 현재 풍속을 double로 반환합니다.
    }
    public String getWeatherDescription() {
    	return currentWeatherDescription;
    }
    public String getIconUrl() {
    	return iconUrl;
    }
    public Object getCityMessage() {
		return city+"의 날씨는 ";
	}
    public String getTemperatureMeesage() {
        DecimalFormat df = new DecimalFormat("#.##"); // 소수점 두 자리까지 표현하는 DecimalFormat 객체 생성
        String temp=df.format(currentTemperature);
    	if(currentTemperature<10)
    		return temp+"도로 추울것으로 예상되니 따뜻하게 입고 외출하세요. ";
    	else if(currentTemperature>25) {
    		return temp+"도로 더울것으로 예상되니 시원하게 입고 외출하세요. ";
    	}
    	else 
    		return temp+"도로 선선할것이니 가벼운 겉옷을 챙기시는 것을 추천드려요. ";
    }
    public String getHumidityMessage() {
    	return "습도는 "+currentHumidity+"%입니다. ";
    }
    public String getWindSpeedMeesage() {
    	if(currentWindSpeed>2)
    		return "풍속은 "+currentWindSpeed+"m/s로 강한 바람이 불 예정입니다. ";
    	else 
    		return "풍속은 "+currentWindSpeed+"m/s ";
    }
    public String getWeatherDescriptionMessage() {
    	return "하늘 상태는 "+currentWeatherDescription+"입니다.  ";
    }
    private void API() {
        String apiKey = "";
        if(city.equals("default"))
        	city = "Seoul";
        currentWeatherUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

        try {
        	JSONObject currentWeatherData = fetchWeatherData(currentWeatherUrl);
        	JSONObject main = currentWeatherData.getJSONObject("main");
        	double currentTemperatureKelvin = main.getDouble("temp");
        	double maxTemperatureKelvin = main.getDouble("temp_max");
        	double minTemperatureKelvin = main.getDouble("temp_min");
        	int currentHumidity = main.getInt("humidity");
        	double currentWindSpeed = currentWeatherData.getJSONObject("wind").getDouble("speed");

        	JSONArray weatherArray = currentWeatherData.getJSONArray("weather");
        	String currentWeatherDescription = weatherArray.getJSONObject(0).getString("description");
        	double currentTemperatureFahrenheit = (currentTemperatureKelvin - 273.15);
            double maxTemperatureFahrenheit = (maxTemperatureKelvin - 273.15);
            double minTemperatureFahrenheit = (minTemperatureKelvin - 273.15);
            
            
            String iconCode = weatherArray.getJSONObject(0).getString("icon"); // 날씨 상태 코드를 얻어옵니다.

            // 아이콘 이미지 URL을 얻어옵니다.
            iconUrl = getWeatherIconUrl(iconCode);

            // 아이콘 이미지를 로드하여 JLabel에 설정합니다.
            
            // 변수 초기화
            this.currentTemperature = currentTemperatureFahrenheit;
            this.maxTemperature = maxTemperatureFahrenheit;
            this.minTemperature = minTemperatureFahrenheit;
            this.currentWindSpeed = currentWindSpeed;
            this.currentHumidity=currentHumidity;
            this.currentWeatherDescription = currentWeatherDescription;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        private static JSONObject fetchWeatherData(String apiUrl) throws Exception {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return new JSONObject(response.toString());
        }
        private String getWeatherIconUrl(String iconCode) {
            return "http://openweathermap.org/img/wn/" + iconCode + ".png";
        }
		
}
