package entity.user;

import static entity.user.UserTextConstants.*;

public enum UserStatus {	

	VIP			(ID_VIP_STATUS, RATIOPAY_VIP_STATUS),
	STANDART	(ID_STANDART_STATUS, RATIOPAY_STANDART_STATUS),
	BLOCKED		(ID_BLOCKED_STATUS, RATIOPAY_BLOCKED_STATUS);
	
	private int id;
	private double ratioPay;	

	private UserStatus(int id, double ratioPay) {
		this.id = id;
		this.ratioPay = ratioPay;
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double getRatioPay() {
		return ratioPay;
	}

	public void setRatioPay(double ratioPay) {
		this.ratioPay = ratioPay;
	}
	
}
