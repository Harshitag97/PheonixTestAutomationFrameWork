package api.test.pojo;

public class UserCredentialLogin {

	private String username ;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserCredentialLogin [username=" + username + ", password=" + password + "]";
	}
	public UserCredentialLogin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	
}
