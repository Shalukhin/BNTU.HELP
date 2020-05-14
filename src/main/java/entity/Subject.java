package entity;

public class Subject {
	
	private int id;
	private String nameSubject;
	
	public Subject() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameSubject() {
		return nameSubject;
	}

	public void setNameSubject(String nameSubject) {
		this.nameSubject = nameSubject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nameSubject == null) ? 0 : nameSubject.hashCode());
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
		Subject other = (Subject) obj;
		if (id != other.id)
			return false;
		if (nameSubject == null) {
			if (other.nameSubject != null)
				return false;
		} else if (!nameSubject.equals(other.nameSubject))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", nameSubject=" + nameSubject + "]";
	}
	
}
