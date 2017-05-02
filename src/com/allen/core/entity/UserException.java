package com.allen.core.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UserException : 用户自定义异常
 *
 */
public class UserException extends RuntimeException {

	Logger logger = LoggerFactory.getLogger(UserException.class);
	
    /**
     * 异常发生时间
     */
    private long date = System.currentTimeMillis();

    public UserException() {
    	super();
    }
    
    public UserException(String message) {
		super(message);
		logger.info(String.valueOf(getDate()));
	}

	public long getDate() {
        return date;
    }
}
