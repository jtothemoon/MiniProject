package Model;

public class EventDTO {
	private int eventNumber;
	private String eventText;
	private String eventAns1;
	private String ebentAns2;

	public EventDTO(int eventNumber, String eventText, String eventAns1, String ebentAns2) {
		this.eventNumber = eventNumber;
		this.eventText = eventText;
		this.eventAns1 = eventAns1;
		this.ebentAns2 = ebentAns2;
	}

	public int getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(int eventNumber) {
		this.eventNumber = eventNumber;
	}

	public String getEventText() {
		return eventText;
	}

	public void setEventText(String eventText) {
		this.eventText = eventText;
	}

	public String getEventAns1() {
		return eventAns1;
	}

	public void setEventAns1(String eventAns1) {
		this.eventAns1 = eventAns1;
	}

	public String getEbentAns2() {
		return ebentAns2;
	}

	public void setEbentAns2(String ebentAns2) {
		this.ebentAns2 = ebentAns2;
	}
	
	
}
