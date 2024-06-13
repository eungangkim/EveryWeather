/*
 * 사용자가 설정한 시간을 저장하고 관리함
 */

import java.util.ArrayList;

public class TimeSet {
	private ArrayList<String> timelist;
	public TimeSet() {
		timelist=new ArrayList<>();
	}
	public void addTime(String time) {
		timelist.add(time);
	}
	public String remove(int index) {
		String time=timelist.get(index);
		timelist.remove(index);
		return time;
	}
	public Iterable<String> vals(){
		return timelist;
	}
}
