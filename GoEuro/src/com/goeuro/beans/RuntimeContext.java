package com.goeuro.beans;


public class RuntimeContext {
	
	private String fileLoc;
	private String city;
	private String fileType;
	private String writerClass;
	
	public String getFileLoc() {
		return fileLoc;
	}
	public void setFileLoc(String fileLoc) {
		this.fileLoc = fileLoc;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getWriterClass() {
		return writerClass;
	}
	public void setWriterClass(String writerClass) {
		this.writerClass = writerClass;
	}
	
	@Override
	public String toString() {
		return "RuntimeContext [ fileLoc=" + fileLoc + ", city=" + city + ", fileType=" + fileType + ", writerClass=" + writerClass + " ]";
	}
	
	
}
