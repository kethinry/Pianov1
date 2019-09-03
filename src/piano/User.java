package piano;

public class User {
	private String  UserName,Password,UserBirYear,UserBirMonth,UserBirDay,PhoneNumber,Email;
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String UserName) {
		this.UserName = UserName;
	}
	public String getPassword() {
		return Password;
	}
	public String getUserBirYear() {
		return UserBirYear;
	}
	public void setUserBirYear(String userBirYear) {
		this.UserBirYear = userBirYear;
	}
	public void setUserBirMonth(String userBirMonth) {
		this.UserBirMonth = userBirMonth;
	}
	public void setUserBirDay(String userBirDay) {
		this.UserBirDay = userBirDay;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.PhoneNumber = phoneNumber;
	}
	public void setEmail(String email) {
		this.Email = email;
	}
	public String getUserBirMonth() {
		return UserBirMonth;
	}
	public String getUserBirDay() {
		return UserBirDay;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public String getEmail() {
		return Email;
	}
	public void setPassword(String Password) {
		this.Password = Password;
	}
	public User( String UserName, String Password, String UserBirYear, String UserBirMonth, String UserBirDay, String PhoneNumber, String Email) {
		super();
		this.UserName = UserName;
		this.Password = Password;
		this.UserBirYear = UserBirYear;
	    this.UserBirMonth = UserBirMonth;
	    this.UserBirDay = UserBirDay;
	    this.PhoneNumber = PhoneNumber;
	    this.Email = Email;
	}
	public User() {
		
	}

}
