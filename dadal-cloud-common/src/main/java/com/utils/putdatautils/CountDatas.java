/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.utils.putdatautils   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年2月13日下午7:23:02
 * @version V1.0
 */

package com.utils.putdatautils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Frank
 * @date: 2020年2月13日 下午7:23:02
 */

public class CountDatas {

	private static final Logger logger = LoggerFactory.getLogger(CountDatas.class);

	public static void countTotal() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		try {
			conn = ConnUtils.getConnection();
			if (conn == null) {
				throw new NullPointerException("conn can not be null!");
			} else {
				logger.info("Oracle Connect Successful!");
			}
			String sql = "SELECT COUNT(1) FROM TB";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			metaData = rs.getMetaData();

			HashMap<String, Object> map = new HashMap<>();

			while (rs.next()) {
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					String columnLable = metaData.getColumnLabel(i + 1);
					Object columnValue = rs.getObject(columnLable);
					map.put(columnLable, columnValue);
				}
			}
			if (map.get("COUNT(1)") != null) {
				logger.info("The total num is: {}", map.get("COUNT(1)"));
			}

		} catch (Exception e) {
			logger.error("error occured!!");
		} finally {
			ConnUtils.closeResources(rs, ps, conn);
		}
	}

	/**
	 * Count the total num of the table
	 */
	public static void main(String[] args) {
		CountDatas.countTotal();
	}

}
