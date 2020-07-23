package com.modules.assist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Table;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 根据实体类生成数据库表创建语句
 * @author: Frankjiu
 * @date: 2020年6月10日
 */
@Slf4j
public class GenerateSqlController {

	// 实体类所在的package在磁盘上的绝对路径
	private static String packageName = "D:/eclipse/workspace/dadal-cloud-assist/src/main/java/com/entity/";
	// 项目中实体类的路径
	private static String prefix = "com.entity.";
	// 生成sql的文件夹
	private static String filePath = "E:/created/";
	// 生成sql文件名
	private static String fileName = "sqls.sql";
	// 表名
	private static final String TABLE_NAME = "tableName";
	// 类名
	private static final String CLASS_NAME = "className";

	public static void main(String[] args) {
		StringBuilder sqls = new StringBuilder();
		// 获取表名(@Table注解值或者包下的类名称)
		List<Map<String, String>> list = getAllClasses(packageName);
		for (Map<String, String> map : list) {
			String tableName = map.get(TABLE_NAME);
			String className = prefix + map.get(CLASS_NAME);
			String sql = generateSql(tableName, className);
			sqls.append(sql);
		}
		stringToSql(sqls.toString(), filePath + fileName);
	}

	/**
	 * 将实体类中含大写的属性转为带下划线的数据库字段
	 */
	public static String getColumnName(String field) {
		for (int i = 0; i < field.length(); i++) {
			char c = field.charAt(i);
			if (Character.isUpperCase(c)) {
				char lc = Character.toLowerCase(c);
				field = field.substring(0, i) + "_" + lc + field.substring(i + 1);
			}
		}
		return field;
	}

	/**
	 * 根据实体类生成建表语句
	 * 
	 * @param className 全类名
	 * @param filePath 磁盘路径 如 : D:/workspace/
	 */
	public static String generateSql(String tableName, String className) {
		try {
			Class<?> clz = Class.forName(className);
			Field[] fields = clz.getDeclaredFields();

			String varchar = " varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,";
			StringBuilder commonColumn = new StringBuilder();
			StringBuilder afterSql = new StringBuilder();
			for (int i = 0; i < fields.length; i++) {
				String columnName = getColumnName(fields[i].getName());
				if (i == 0) {
					afterSql.append(" PRIMARY KEY (`" + columnName + "`) USING BTREE,")
							.append("\n INDEX `" + "idx_" + columnName + "`(`" + columnName + "`) USING BTREE");
				}
				commonColumn.append(" \n `" + columnName + "`").append(varchar);
			}
			StringBuilder sql = new StringBuilder();
			sql.append("\r\n DROP TABLE IF EXISTS `" + tableName + "`; ").append(" \n CREATE TABLE `" + tableName + "`  (")
					.append(" " + commonColumn).append(" \n " + afterSql)
					.append(" \n ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci; \n");
			return sql.toString();
		} catch (ClassNotFoundException e) {
			log.info("该类未找到！");
			return null;
		}
	}

	/**
	 * 获取类的类名及其@Table注解的表名 放入map.
	 */
	public static <T> Map<String, String> getClassAndTableName(Class<T> clazz) {
		Map<String, String> map = new ConcurrentHashMap<>();
		Table table = clazz.getAnnotation(Table.class);
		String className = clazz.getSimpleName();
		map.put(TABLE_NAME, className);
		map.put(CLASS_NAME, className);
		if (table != null && table.name() != null) {
			map.put(TABLE_NAME, table.name());
		}
		return map;
	}

	/**
	 * 获取包下所有类的名称以及对应表名放入List<Map>.
	 */
	public static List<Map<String, String>> getAllClasses(String packageName) {
		List<Map<String, String>> list = new ArrayList<>();
		String classNamePath = "";
		File f = new File(packageName);
		if (f.exists() && f.isDirectory()) {
			File[] files = f.listFiles();
			for (File file : files) {
				classNamePath = prefix + file.getName();
				classNamePath = classNamePath.substring(0, classNamePath.lastIndexOf("."));
				Class<?> cla;
				try {
					cla = Class.forName(classNamePath);
					Map<String, String> map = getClassAndTableName(cla);
					list.add(map);
				} catch (ClassNotFoundException e) {
					log.info(e.getMessage());
				}
			}
		} else {
			log.info("不存在该包路径!");
		}
		return list;
	}

	/**
	 * 将生成的建表语句写入文件
	 */
	public static void stringToSql(String str, String path) {
		byte[] sourceByte = str.getBytes();
		if (null != sourceByte) {
			FileOutputStream outStream = null;
			try {
				File file = new File(path); // 文件路径（路径+文件名）
				if (!file.exists()) { // 文件不存在则创建文件，先创建目录
					File dir = new File(file.getParent());
					dir.mkdirs();
					boolean flag = file.createNewFile();
					if (!flag) {
						log.info("文件创建失败!");
					} else {
						log.info("文件已创建!");
					}
				}
				// 文件输出流用于将数据写入文件
				outStream = new FileOutputStream(file);
				outStream.write(sourceByte);
				outStream.flush();
				outStream.close(); //关闭文件输出流
				log.info(">>>>>>建表sql文件生成成功!>>>>>>");
			} catch (Exception e) {
				log.info(e.getMessage());
			} finally {
				if (outStream != null) {
					try {
						outStream.close();
					} catch (IOException e) {
						log.info(e.getMessage());
					}
				}
			}
		}
	}
}