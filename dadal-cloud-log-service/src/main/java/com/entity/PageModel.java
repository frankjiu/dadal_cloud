/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.entity   
 * @author: Frankjiu
 * @date: 2020年6月3日
 * @version: V1.0
 */

package com.entity;

import java.util.List;

import lombok.Data;

/**
 * @Description: PageModel
 * @author: Frankjiu
 * @date: 2020年6月3日
 */
@Data
public class PageModel<E> {
	private int pageNum; //当前页码
	private int pageSize; //每页显示条数

	private int totalCount; //总记录数
	private int totalPages; //总页数
	private int startIndex; //开始索引值

	private int prePage; //上一页
	private int nextPage; //下一页
	private int startPage; //首页
	private int endPage; //尾页

	private List<E> list;

	public PageModel(int pageNum, int pageSize, int totalCount) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalCount = totalCount;

		totalPages = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1); //总页数
		startIndex = (pageNum - 1) * pageSize < 0 ? 0 : (pageNum - 1) * pageSize; //开始索引值
		prePage = (pageNum > 0) ? (pageNum - 1) : (pageNum); //上一页
		nextPage = (pageNum < totalPages) ? (pageNum + 1) : (pageNum); //下一页

		startPage = 0; //首页
		endPage = totalPages; //尾页
	}

	/**
	 * 是否有上一页
	 */
	public boolean hasPrev() {
		return pageNum > startPage;
	}

	/**
	 * 是否有下一页
	 */
	public boolean hasNext() {
		return pageNum + 1 < totalPages;
	}

}
