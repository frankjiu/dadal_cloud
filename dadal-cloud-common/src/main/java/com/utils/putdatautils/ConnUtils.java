/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.utils.putdatautils   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年2月13日下午7:10:34
 * @version V1.0
 */

package com.utils.putdatautils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Frankjiu
 * @date: 2020年2月13日 下午7:10:34
 */

public class ConnUtils {

	private static final Logger logger = LoggerFactory.getLogger(ConnUtils.class);

	private static final String URL = "jdbc:oracle:this:@localhost:jiu";
	private static final String USER = "jiu";
	private static final String PASSWORD = "jiu";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	private ConnUtils() {
	}

	// Register the Driver
	static {
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			throw new ExceptionInInitializerError();
		}
	}

	// get connection resource
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	// close the resource
	public static void closeResources(ResultSet rs, Statement st, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				logger.info(e.getMessage(), e);
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				logger.info(e.getMessage(), e);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				logger.info(e.getMessage(), e);
			}
		}

	}

}
