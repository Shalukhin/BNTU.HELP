package builder;

import entity.Subject;

public class SubjectBuilder {
	
	private Subject subject = new Subject();
	
	public SubjectBuilder createNewSubject() {
		subject = new Subject();
		return this;
	}
	
	public SubjectBuilder withId(int id) {
		subject.setId(id);
		return this;
	}
	
	public SubjectBuilder withNameSubject(String nameSubject) {
		subject.setNameSubject(nameSubject);
		return this;
	}
	
	public Subject build() {
		return subject;
	}	

}
