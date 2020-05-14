package builder;

import java.io.InputStream;

import entity.FinishFile;

public class FinishFileBuilder {
	
	private FinishFile finishFile = new FinishFile();
	
	public FinishFileBuilder createNewFinishFile() {
		finishFile = new FinishFile();
		return this;
	}
	
	public FinishFileBuilder withId(int id) {
		finishFile.setId(id);
		return this;
	}
	
	public FinishFileBuilder withNameFinishFile(String nameFinishFile) {
		finishFile.setNameFinishFile(nameFinishFile);
		return this;
	}
	
	public FinishFileBuilder withDataFinishFile(InputStream dataFinishFile) {
		finishFile.setDataFinishFile(dataFinishFile);
		return this;
	}
	
	public FinishFile build() {
		return finishFile;
	}

}
