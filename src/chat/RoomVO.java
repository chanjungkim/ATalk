package chat;

public class RoomVO {
	private String title;
	private String masterID;
	private int population;
	private String language;
	private String password;
	private int portNum;
	
	//TITLE, MASTERID, POPULATION, LANGUAGE, PW
	public RoomVO(String title, String masterID, int population, String language, String password, int portNum) { //회원가입
		this.title = title;
		this.masterID = masterID;
		this.population = population;
		this.language = language;
		this.password = password;
		this.portNum = portNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMasterID() {
		return masterID;
	}

	public void setMasterID(String masterID) {
		this.masterID = masterID;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population ;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}
	
	public int getPortNum() {
		return portNum;
	}

	@Override
	public String toString() {
		return "RoomVO [title=" + title + ", masterID=" + masterID + ", population=" + population + ", language="
				+ language + ", password=" + password + "]";
	}
}
