package chat;

public class DbVO {
	private String id;
	private String pw;
	private String name;
	private String birth;
	private String eMail;
	private String phone;
	
	private String blackId;//블랙리스트 테이블 
	
	public DbVO() {
	}
	
	public DbVO(String blackId) { //블랙리스트 등록
		this.blackId = blackId;
	}

	public DbVO(String id, String pw, String name, String birth, String eMail, String phone) { //회원가입
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.eMail = eMail;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "DbVO [id=" + id + ", pw=" + pw + ", name=" + name + ", birth=" + birth + ", eMail=" + eMail
				+ ", phone=" + phone + "]";
	}
}
