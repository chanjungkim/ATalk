package chat;

public class RoomVO {
	String title;
	String masterID;
	int population;
	String language;
	String password;
	
	//TITLE, MASTERID, POPULATION, LANGUAGE, PW
	public RoomVO(String title, String masterID, int population, String language, String password) { //회원가입
		this.title = title;
		this.masterID = masterID;
		this.population = population;
		this.language = language;
		this.password = password;
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

	@Override
	public String toString() {
		return "RoomVO [title=" + title + ", masterID=" + masterID + ", population=" + population + ", language="
				+ language + ", password=" + password + "]";
	}
}
