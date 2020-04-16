package builder;

import entity.Role;

public class RoleBuilder {
	
	private Role role = new Role();
	
	public RoleBuilder createNewRole() {
		role = new Role();
		return this;
	}
	
	public RoleBuilder withId (int id) {
		role.setId(id);
		return this;
	}
	
	public RoleBuilder withNameRole(String nameRole) {
		role.setNameRole(nameRole);
		return this;
	}
	
	public Role build() {
		return role;
	}
	

}
