package entity;

public class Role {
	
	private int id;
	private String nameRole;
	
	public Role() {
		super();
	}
	
	public Role(int id, String nameRole) {
		super();
		this.id = id;
		this.nameRole = nameRole;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nameRole == null) ? 0 : nameRole.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		if (nameRole == null) {
			if (other.nameRole != null)
				return false;
		} else if (!nameRole.equals(other.nameRole))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", nameRole=" + nameRole + "]";
	}
	
	

}
