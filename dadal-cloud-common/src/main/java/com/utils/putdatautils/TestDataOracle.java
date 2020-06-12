/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.utils.putdatautils   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年2月13日下午8:03:32
 * @version V1.0
 */

package com.utils.putdatautils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author: Frankjiu
 * @date: 2020年2月13日 下午8:03:32
 */

public class TestDataOracle extends JdbcDaoSupport implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(TestDataOracle.class);

	// thread name
	private String name;

	// datas numbers produced by one thread
	private int nums;

	// start primary key of table
	private int startId = 0;

	// end primary key of table
	private int endId = 1;

	public TestDataOracle(String name, int nums, int startId, int endId) {
		super();
		this.name = name;
		this.nums = nums;
		this.startId = startId;
		this.endId = endId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public int getStartId() {
		return startId;
	}

	public void setStartId(int startId) {
		this.startId = startId;
	}

	public int getEndId() {
		return endId;
	}

	public void setEndId(int endId) {
		this.endId = endId;
	}

	public static Logger getLogger() {
		return logger;
	}

	@Override
	public void run() {
		Connection conn = null;
		CallableStatement st = null;
		ResultSet rs = null;
		try {
			conn = ConnUtils.getConnection();
			if (conn != null) {
				logger.info(">>> Oracle connect successful!");
			}

			logger.info(">>> {} start to call..., 开始id{}, 结束id{}.", this.name, this.startId, this.endId);
			long start = System.currentTimeMillis();
			String sql = "{CALL PRO_INSERT_TEST_DATA(?,?,?)}";
			if (conn == null) {
				throw new NullPointerException("conn can not be null!");
			}
			st = conn.prepareCall(sql);
			logger.info(">>> Procedure compiled successful!");
			st.setInt(1, nums);
			st.setInt(2, startId);
			st.setInt(3, endId);
			/**
			 * 更新使用st.executeUpdate()
			 */
			boolean flag = st.execute();
			if (flag) {
				logger.info("Procedure execute successful!!");
			} else {
				logger.info("Procedure execute failure!!");
			}
			long end = System.currentTimeMillis();
			logger.info("{} cost {} s", this.name, (end - start) / 1000);
		} catch (Exception e) {
			logger.error("Error occured while executing procedure!");
		} finally {
			ConnUtils.closeResources(rs, st, conn);
		}

	}

}
