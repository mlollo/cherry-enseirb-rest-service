package cherry.robothandlers.service;

import java.util.ArrayList;
import java.util.List;

//@Document(collection="robot")
public class Robot {
	//@Id
	private String name;
	private String ip;
	private boolean isSpeaking;
	private boolean isMoving;
	private boolean isTaken;
	private List<String> primList;
	private List<String> speechList;


	//@PersistenceConstructor
	public Robot(){
		name = "no_name";
		ip ="0";
		isSpeaking = false;
		isMoving = false;
		isTaken = false;
		setPrimList(new ArrayList<String>());
		setSpeechList(new ArrayList<String>());
	}
	
	public boolean isTaken() {
		return isTaken;
	}

	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}

	public List<String> getSpeechList() {
		return speechList;
	}

	public void setSpeechList(List<String> speechList) {
		this.speechList = speechList;
	}

	public void setSpeaking(boolean isSpeaking) {
		this.isSpeaking = isSpeaking;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = "http://" + ip;
		//test
		//his.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsSpeaking() {
		return isSpeaking;
	}

	public void setIsSpeaking(boolean isSpeaking) {
		this.isSpeaking = isSpeaking;
	}

	public boolean getIsMoving() {
		return isMoving;
	}

	public void setIsMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public List<String> getPrimList() {
		return primList;
	}

	public void setPrimList(List<String> primList) {
		this.primList = primList;
	}  
	
	@Override
    public String toString()
	{
            return "Robot [name = " + this.getName() + ", ip = " + this.getIp() + "]";
    }
}
