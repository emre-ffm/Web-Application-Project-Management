package edu.fra.uas.userdto;


 //Datatransferobject that carries data between processes. Its used to carries the used Data so the user dont see all the values in Model
public class UserRegistrationDto {

	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
