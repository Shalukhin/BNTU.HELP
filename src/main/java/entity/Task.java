package entity;

import java.math.BigDecimal;

public class Task {
	
	private int id;
	private String nameTask;
	private BigDecimal priceTask;
	private Course course;
	private Subject subject;
	
	public Task() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameTask() {
		return nameTask;
	}

	public void setNameTask(String nameTask) {
		this.nameTask = nameTask;
	}

	public BigDecimal getPriceTask() {
		return priceTask;
	}

	public void setPriceTask(BigDecimal priceTask) {
		this.priceTask = priceTask;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + id;
		result = prime * result + ((nameTask == null) ? 0 : nameTask.hashCode());
		result = prime * result + ((priceTask == null) ? 0 : priceTask.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		Task other = (Task) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (id != other.id)
			return false;
		if (nameTask == null) {
			if (other.nameTask != null)
				return false;
		} else if (!nameTask.equals(other.nameTask))
			return false;
		if (priceTask == null) {
			if (other.priceTask != null)
				return false;
		} else if (!priceTask.equals(other.priceTask))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", nameTask=" + nameTask + ", priceTask=" + priceTask + ", course=" + course
				+ ", subject=" + subject + "]";
	}
	
}
