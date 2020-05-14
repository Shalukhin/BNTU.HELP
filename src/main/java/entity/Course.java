package entity;

public class Course {
	
	private int id;
	private int numberCourse;
	
	public Course() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberCourse() {
		return numberCourse;
	}
	
	public String getNumberCourseStr() {
		return String.valueOf(numberCourse);
	}

	public void setNumberCourse(int numberCourse) {
		this.numberCourse = numberCourse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + numberCourse;
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
		Course other = (Course) obj;
		if (id != other.id)
			return false;
		if (numberCourse != other.numberCourse)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", numberCourse=" + numberCourse + "]";
	}	

}
