package builder;

import entity.Status;

public class StatusBuilder {
	
	private Status status = new Status();
	
	public StatusBuilder createNewStatus() {
		status = new Status();
		return this;
	}
	
	public StatusBuilder withId(int id) {
		status.setId(id);
		return this;		
	}
	
	public StatusBuilder withNameStatus (String nameStatus) {
		status.setNameStatus(nameStatus);
		return this;
	}
	
	public StatusBuilder withRatioPay (double ratioPay) {
		status.setRatioPay(ratioPay);
		return this;
	}
	
	public Status build() {
		return status;
	}

}
