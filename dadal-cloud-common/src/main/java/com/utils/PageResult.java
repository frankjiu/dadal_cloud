package com.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class PageResult<T> {
	
	@Setter
	@Getter
	private Integer curPage; // 当前页
	
	@Setter
	@Getter
	private Integer pageCount; // 总页数
	
	@Setter
	@Getter
	private Long recordCount; // 数据总条数
	
	@Setter
	@Getter
	private List<T> companyList = new ArrayList<T>(); // 结果集
	
}