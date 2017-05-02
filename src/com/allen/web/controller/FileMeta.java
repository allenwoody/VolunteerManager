package com.allen.web.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//ignore "bytes" when return json format
@JsonIgnoreProperties({"bytes"}) 
public class FileMeta {

	private String fileName;
	private String fileSize;
	private String fileType;
	
	private byte[] bytes;

	//setters & getters
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	
}