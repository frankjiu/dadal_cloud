/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @author: Frankjiu
 * @date: 2020年6月3日
 * @version: V1.0
 */

package com.result;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;

/**
 * @Description: 分页封装
 * @author: Frankjiu
 * @date: 2020年6月3日
 */
@Data
public class PageModel<E> {

    /** 当前页码 */
    private long pageNum;
    /** 每页显示条数 */
    private long pageSize;
    /** 总记录数 */
    private long totalCount;
    /** 总页数 */
    private long totalPages;
    /** 开始索引值 */
    private long startIndex;
    /** 上一页 */
    private long prePage;
    /** 下一页 */
    private long nextPage;
    /** 首页 */
    private long startPage;
    /** 尾页 */
    private long endPage;
    /** 数据 */
    private Collection<E> data = new ArrayList<>();

    public PageModel() {
    }

    public PageModel(long pageNum, long pageSize, long totalCount) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;

        totalPages = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1);
        startIndex = (pageNum - 1) * pageSize < 0 ? 0 : (pageNum - 1) * pageSize;
        prePage = (pageNum > 0) ? (pageNum - 1) : (pageNum);
        nextPage = (pageNum < totalPages) ? (pageNum + 1) : (pageNum);

        startPage = 0;
        endPage = totalPages;
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
