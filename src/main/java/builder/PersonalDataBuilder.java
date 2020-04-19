package builder;

import java.math.BigDecimal;

import entity.PersonalData;

public class PersonalDataBuilder {
	
	private PersonalData personalData = new PersonalData();
	
	public PersonalDataBuilder createNewPersonalData() {
		personalData = new PersonalData();
		personalData.setBonusMoney(new BigDecimal(0));
		personalData.setIdInvitingUser(0);
		return this;
	}
	
	public PersonalDataBuilder withIdUser(int idUser) {
		personalData.setIdUser(idUser);
		return this;
	}
	
	public PersonalDataBuilder withName(String name) {
		personalData.setName(name);
		return this;
	}
	
	public PersonalDataBuilder withPhone(String phone) {
		personalData.setPhone(phone);
		return this;
	}
	
	public PersonalDataBuilder withEmail(String email) {
		personalData.setEmail(email);
		return this;
	}
	
	public PersonalDataBuilder withBonusMoney (BigDecimal bonusMoney) {
		personalData.setBonusMoney(bonusMoney);
		return this;
	}
	
	public PersonalDataBuilder withIdInvitingUser (int idInvitingUser) {
		personalData.setIdInvitingUser(idInvitingUser);
		return this;
	}
	
	public PersonalData build() {
		return personalData;
	}
}
