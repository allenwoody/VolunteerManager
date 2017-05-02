package com.allen.core.entity;

/**
 * DaoException : 封装Dao(数据库访问)层发生的异常
 *
 */
public class DaoException extends UserException {

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

}
