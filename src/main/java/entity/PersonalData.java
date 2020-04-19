package entity;

import java.math.BigDecimal;

public class PersonalData {
	
	private int idUser;
	private String name;
	private String phone;
	private String email;
	private BigDecimal bonusMoney;
	private int idInvitingUser;
	
	public PersonalData() {
		super();
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getBonusMoney() {
		return bonusMoney;
	}

	public void setBonusMoney(BigDecimal bonusMoney) {
		this.bonusMoney = bonusMoney;
	}

	public int getIdInvitingUser() {
		return idInvitingUser;
	}

	public void setIdInvitingUser(int idInvitingUser) {
		this.idInvitingUser = idInvitingUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonusMoney == null) ? 0 : bonusMoney.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + idInvitingUser;
		result = prime * result + idUser;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		PersonalData other = (PersonalData) obj;
		if (bonusMoney == null) {
			if (other.bonusMoney != null)
				return false;
		} else if (!bonusMoney.equals(other.bonusMoney))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idInvitingUser != other.idInvitingUser)
			return false;
		if (idUser != other.idUser)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PersonalData [idUser=" + idUser + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", bonusMoney=" + bonusMoney + ", idInvitingUser=" + idInvitingUser + "]";
	}	
	
}
