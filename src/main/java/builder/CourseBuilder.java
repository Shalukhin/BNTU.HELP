package builder;

import entity.Course;

public class CourseBuilder {
	
	private Course course = new Course();
	
	public CourseBuilder createNewCourse() {
		course = new Course();
		return this;
	}
	
	public CourseBuilder withId(int id) {
		course.setId(id);
		return this;
	}
	
	public CourseBuilder withNumberCourse(int numberCourse) {
		course.setNumberCourse(numberCourse);
		return this;
	}
	
	public Course build() {
		return course;
	}

}
