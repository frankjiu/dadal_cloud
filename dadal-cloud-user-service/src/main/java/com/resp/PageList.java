package com.resp;

import java.util.ArrayList;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageList<E> {
    private Collection<E> data = new ArrayList<>();
    private long totalNum;

    /*public PageList(IPage<E> page) {
    	this.data = page.getRecords();
    	this.totalNum = page.getTotal();
    }*/

}