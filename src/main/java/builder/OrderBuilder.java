package builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import entity.FinishFile;
import entity.Order;
import entity.Task;
import entity.User;

public class OrderBuilder {
	
	private Order order = new Order();
	
	public OrderBuilder createNewOrder() {
		order = new Order();
		return this;
	}
	
	public OrderBuilder withId (int id) {
		order.setId(id);
		return this;
	}
	
	public OrderBuilder withUser (User user) {
		order.setUser(user);
		return this;
	}
	
	public OrderBuilder withTask (Task task) {
		order.setTask(task);
		return this;
	}
	
	public OrderBuilder withRealizer (User realizer) {
		order.setRealizer(realizer);;
		return this;
	}
	
	public OrderBuilder withNote (String note) {
		order.setNote(note);
		return this;
	}
	
	public OrderBuilder withAdjustedPriceTask (BigDecimal adjustedPriceTask) {
		order.setAdjustedPriceTask(adjustedPriceTask);
		return this;
	}
	
	public OrderBuilder withPriceOrder (BigDecimal priceOrder) {
		order.setPriceOrder(priceOrder);
		return this;
	}
	
	public OrderBuilder withIsProcessed (boolean isProcessed) {
		order.setProcessed(isProcessed);
		return this;
	}
	
	public OrderBuilder withIsConfirmed (boolean isConfirmed) {
		order.setConfirmed(isConfirmed);
		return this;
	}
	
	public OrderBuilder withIsPaid (boolean isPaid) {
		order.setPaid(isPaid);
		return this;
	}
	
	public OrderBuilder withIsCompleted (boolean isCompleted) {
		order.setCompleted(isCompleted);
		return this;
	}
	
	public OrderBuilder withDateCreate(LocalDateTime dateCreate) {
		order.setDateCreate(dateCreate);
		return this;
	}
	
	public OrderBuilder withDateProcess(LocalDateTime dateProcess) {
		order.setDateProcess(dateProcess);
		return this;
	}
	
	public OrderBuilder withDateConfirm(LocalDateTime dateConfirm) {
		order.setDateConfirm(dateConfirm);
		return this;
	}
	
	public OrderBuilder withDatePay(LocalDateTime datePay) {
		order.setDatePay(datePay);
		return this;
	}
	
	public OrderBuilder withDateComplete(LocalDateTime dateComplete) {
		order.setDateComplete(dateComplete);
		return this;
	}
	
	public OrderBuilder withFinishFile(FinishFile finishFile) {
		order.setFinishFile(finishFile);
		return this;
	}
	
	public Order build() {
		if(order.getUser() != null && order.getTask() != null && order.getPriceOrder() == null) {
			order.setPriceOrder(order.getTask().getPriceTask().multiply(new BigDecimal(order.getUser().getStatus().getRatioPay())).setScale(2));
		}
		return order;
	}

}
