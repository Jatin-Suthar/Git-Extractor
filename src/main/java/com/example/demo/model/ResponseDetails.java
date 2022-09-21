package com.example.demo.model;

public class ResponseDetails {
	String msg;
	boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public ResponseDetails() {}
	
	public ResponseDetails(String msg) {
		this.msg=msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
