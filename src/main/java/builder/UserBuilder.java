package builder;

import entity.Role;
import entity.Status;
import entity.User;

public class UserBuilder {
	
	private User user = new User();
	
	public UserBuilder createNewUser() {
		user = new User();
		return this;
	}
	
	public UserBuilder withId(int id) {
		user.setId(id);
		return this;
	}
	
	public UserBuilder withLogin(String login) {
		user.setLogin(login);
		return this;
	}
	
	public UserBuilder withPassword(String password) {
		user.setPassword(password);
		return this;
	}
	
	public UserBuilder withRole(Role role) {
		user.setRole(role);
		return this;
	}
	
	public UserBuilder withStatus(Status status) {
		user.setStatus(status);
		return this;
	}
	
	public User build() {
		return user;
	}

}
