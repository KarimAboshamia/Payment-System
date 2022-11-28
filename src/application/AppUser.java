package application;

public abstract class AppUser {
	private String password;
	private String username;
	private String permission;
	
	public AppUser(String username, String password, String permission) {
		this.username = username;
		this.password = password;
		this.permission = permission;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getPermission() {
		return permission;
	}

}
