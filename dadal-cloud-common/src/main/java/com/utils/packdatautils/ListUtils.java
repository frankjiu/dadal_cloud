/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.utils.packutils   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年2月13日下午4:24:29
 * @version V1.0
 */

package com.utils.packdatautils;

import java.util.AbstractList;
import java.util.List;

/**
 * @author: Frankjiu
 * @date: 2020年2月13日 下午4:24:29 List Spliter
 */

public class ListUtils {

	public static <T> List<List<T>> partition(final List<T> list, final int size) {
		if (list == null) {
			throw new NullPointerException("List must not be null");
		}
		if (size <= 0) {
			throw new IllegalArgumentException("Size must be greater than 0 ");
		}
		return new Partition<T>(list, size);
	}

	private static class Partition<T> extends AbstractList<List<T>> {

		private final List<T> list;
		private final int size;

		public Partition(final List<T> list, final int size) {
			this.list = list;
			this.size = size;
		}

		@Override
		public List<T> get(final int index) {
			final int listSize = size();
			if (listSize < 0) {
				throw new IllegalArgumentException("negative size:" + listSize);
			}
			if (index < 0) {
				throw new IndexOutOfBoundsException("Index" + index + "must not be negative");
			}
			if (index >= listSize) {
				throw new IndexOutOfBoundsException("Index" + index + "must be less than size" + listSize);
			}
			final int start = index * size;
			final int end = Math.min(start + size, list.size());
			return list.subList(start, end);
		}

		@Override
		public int size() {
			return (list.size() + size - 1) / size;
		}

		@Override
		public boolean isEmpty() {
			return list.isEmpty();
		}

	}

}
