package entity;

import java.io.InputStream;

public class FinishFile {
	
	private int id;
	private String nameFinishFile;
	private InputStream dataFinishFile;
	
	public FinishFile() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameFinishFile() {
		return nameFinishFile;
	}

	public void setNameFinishFile(String nameFinishFile) {
		this.nameFinishFile = nameFinishFile;
	}

	public InputStream getDataFinishFile() {
		return dataFinishFile;
	}

	public void setDataFinishFile(InputStream dataFinishFile) {
		this.dataFinishFile = dataFinishFile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nameFinishFile == null) ? 0 : nameFinishFile.hashCode());
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
		FinishFile other = (FinishFile) obj;
		if (id != other.id)
			return false;
		if (nameFinishFile == null) {
			if (other.nameFinishFile != null)
				return false;
		} else if (!nameFinishFile.equals(other.nameFinishFile))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FinishFile [id=" + id + ", nameFinishFile=" + nameFinishFile + ", dataFinishFile=" + dataFinishFile
				+ "]";
	}

}
