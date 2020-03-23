package entity;

public class Status {
	
	private int id;
	private String nameStatus;
	private double ratioPay;
	
	public Status() {
		super();
	}
	
	public Status(int id, String nameStatus, double ratioPay) {
		super();
		this.id = id;
		this.nameStatus = nameStatus;
		this.ratioPay = ratioPay;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNameStatus() {
		return nameStatus;
	}
	public void setNameStatus(String nameStatus) {
		this.nameStatus = nameStatus;
	}
	
	public double getRatioPay() {
		return ratioPay;
	}
	
	public void setRatioPay(double ratioPay) {
		this.ratioPay = ratioPay;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nameStatus == null) ? 0 : nameStatus.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ratioPay);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Status other = (Status) obj;
		if (id != other.id)
			return false;
		if (nameStatus == null) {
			if (other.nameStatus != null)
				return false;
		} else if (!nameStatus.equals(other.nameStatus))
			return false;
		if (Double.doubleToLongBits(ratioPay) != Double.doubleToLongBits(other.ratioPay))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Status [id=" + id + ", nameStatus=" + nameStatus + ", ratioPay=" + ratioPay + "]";
	}
	
}
