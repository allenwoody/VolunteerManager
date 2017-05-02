package com.allen.core.entity;

/**
 * ControllerException : 封装视图层发生的异常
 *
 */
public class ControllerException extends UserException {

	
	public ControllerException() {
		super();
	}

	public ControllerException(String message) {
		super(message);
	}

}
