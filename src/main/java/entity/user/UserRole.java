package entity.user;

import static entity.user.UserTextConstants.*;

public enum UserRole {
	
	ADMIN	(ID_ADMIN_ROLE),
	USER	(ID_USER_ROLE),
	GUEST	(ID_GUEST_ROLE);
	
	private int id;

	private UserRole(int id) {		
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

}
