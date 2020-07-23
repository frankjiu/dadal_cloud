package com.modules.exception.globalexception;

import org.apache.ibatis.exceptions.TooManyResultsException;

public abstract class AbstractService<T> {

	public T findBy(String conditionDto) throws TooManyResultsException {
		try {
			return null; //xxxService.selectOne(conditionDto);
		} catch (Exception e) {
			throw new GlobalException(e.getMessage(), e);
		}
	}

}
