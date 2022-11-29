package application;

public abstract class AppUser {
	private String password;
	private String username;
	private String email;
	private String permission;
	
	public AppUser(String email, String username, String password, String permission) {
		this.username = username;
		this.password = password;
		this.permission = permission;
		this.email = email;
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

	public String getEmail() {
		return email;
	}


}
