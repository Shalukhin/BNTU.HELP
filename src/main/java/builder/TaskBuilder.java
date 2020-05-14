package builder;

import java.math.BigDecimal;

import entity.Course;
import entity.Subject;
import entity.Task;

public class TaskBuilder {
	
	private Task task = new Task();
	
	public TaskBuilder createNewTask() {
		task = new Task();
		return this;
	}
	
	public TaskBuilder withId(int id) {
		task.setId(id);
		return this;
	}
	
	public TaskBuilder withNameTask(String nameTask) {
		task.setNameTask(nameTask);
		return this;
	}
	
	public TaskBuilder withPriceTask(BigDecimal priceTask) {
		task.setPriceTask(priceTask);
		return this;
	}
	
	public TaskBuilder withIdCourse(Course course) {
		task.setCourse(course);		
		return this;
	}
	
	public TaskBuilder withIdSubject(Subject subject) {
		task.setSubject(subject);
		return this;
	}
	
	public Task build() {
		return task;
	}
}
