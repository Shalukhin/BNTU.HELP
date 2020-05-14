package entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
	
	private int id;
	private User user;
	private Task task;
	private User realizer;
	private String note;
	private BigDecimal adjustedPriceTask;
	private BigDecimal priceOrder;
	private boolean isProcessed;
	private boolean isConfirmed;
	private boolean isPaid;
	private boolean isCompleted;
	private LocalDateTime dateCreate;
	private LocalDateTime dateProcess;
	private LocalDateTime dateConfirm;
	private LocalDateTime datePay;
	private LocalDateTime dateComplete;
	private FinishFile finishFile; 
	
	public Order() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public User getRealizer() {
		return realizer;
	}

	public void setRealizer(User realizer) {
		this.realizer = realizer;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getAdjustedPriceTask() {
		return adjustedPriceTask;
	}

	public void setAdjustedPriceTask(BigDecimal adjustedPriceTask) {
		this.adjustedPriceTask = adjustedPriceTask;
	}

	public BigDecimal getPriceOrder() {
		return priceOrder;
	}

	public void setPriceOrder(BigDecimal priceOrder) {
		this.priceOrder = priceOrder;
	}

	public boolean isProcessed() {
		return isProcessed;
	}

	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public LocalDateTime getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}

	public LocalDateTime getDateProcess() {
		return dateProcess;
	}

	public void setDateProcess(LocalDateTime dateProcess) {
		this.dateProcess = dateProcess;
	}

	public LocalDateTime getDateConfirm() {
		return dateConfirm;
	}

	public void setDateConfirm(LocalDateTime dateConfirm) {
		this.dateConfirm = dateConfirm;
	}

	public LocalDateTime getDatePay() {
		return datePay;
	}

	public void setDatePay(LocalDateTime datePay) {
		this.datePay = datePay;
	}

	public LocalDateTime getDateComplete() {
		return dateComplete;
	}

	public void setDateComplete(LocalDateTime dateComplete) {
		this.dateComplete = dateComplete;
	}

	public FinishFile getFinishFile() {
		return finishFile;
	}

	public void setFinishFile(FinishFile finishFile) {
		this.finishFile = finishFile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adjustedPriceTask == null) ? 0 : adjustedPriceTask.hashCode());
		result = prime * result + ((dateComplete == null) ? 0 : dateComplete.hashCode());
		result = prime * result + ((dateConfirm == null) ? 0 : dateConfirm.hashCode());
		result = prime * result + ((dateCreate == null) ? 0 : dateCreate.hashCode());
		result = prime * result + ((datePay == null) ? 0 : datePay.hashCode());
		result = prime * result + ((dateProcess == null) ? 0 : dateProcess.hashCode());
		result = prime * result + ((finishFile == null) ? 0 : finishFile.hashCode());
		result = prime * result + id;
		result = prime * result + (isCompleted ? 1231 : 1237);
		result = prime * result + (isConfirmed ? 1231 : 1237);
		result = prime * result + (isPaid ? 1231 : 1237);
		result = prime * result + (isProcessed ? 1231 : 1237);
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((priceOrder == null) ? 0 : priceOrder.hashCode());
		result = prime * result + ((realizer == null) ? 0 : realizer.hashCode());
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Order other = (Order) obj;
		if (adjustedPriceTask == null) {
			if (other.adjustedPriceTask != null)
				return false;
		} else if (!adjustedPriceTask.equals(other.adjustedPriceTask))
			return false;
		if (dateComplete == null) {
			if (other.dateComplete != null)
				return false;
		} else if (!dateComplete.equals(other.dateComplete))
			return false;
		if (dateConfirm == null) {
			if (other.dateConfirm != null)
				return false;
		} else if (!dateConfirm.equals(other.dateConfirm))
			return false;
		if (dateCreate == null) {
			if (other.dateCreate != null)
				return false;
		} else if (!dateCreate.equals(other.dateCreate))
			return false;
		if (datePay == null) {
			if (other.datePay != null)
				return false;
		} else if (!datePay.equals(other.datePay))
			return false;
		if (dateProcess == null) {
			if (other.dateProcess != null)
				return false;
		} else if (!dateProcess.equals(other.dateProcess))
			return false;
		if (finishFile == null) {
			if (other.finishFile != null)
				return false;
		} else if (!finishFile.equals(other.finishFile))
			return false;
		if (id != other.id)
			return false;
		if (isCompleted != other.isCompleted)
			return false;
		if (isConfirmed != other.isConfirmed)
			return false;
		if (isPaid != other.isPaid)
			return false;
		if (isProcessed != other.isProcessed)
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (priceOrder == null) {
			if (other.priceOrder != null)
				return false;
		} else if (!priceOrder.equals(other.priceOrder))
			return false;
		if (realizer == null) {
			if (other.realizer != null)
				return false;
		} else if (!realizer.equals(other.realizer))
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", task=" + task + ", realizer=" + realizer + ", note=" + note
				+ ", adjustedPriceTask=" + adjustedPriceTask + ", priceOrder=" + priceOrder + ", isProcessed="
				+ isProcessed + ", isConfirmed=" + isConfirmed + ", isPaid=" + isPaid + ", isCompleted=" + isCompleted
				+ ", dateCreate=" + dateCreate + ", dateProcess=" + dateProcess + ", dateConfirm=" + dateConfirm
				+ ", datePay=" + datePay + ", dateComplete=" + dateComplete + ", finishFile=" + finishFile + "]";
	}	
	
}
