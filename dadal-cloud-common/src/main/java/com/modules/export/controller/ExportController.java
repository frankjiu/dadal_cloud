package com.modules.export.controller;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modules.export.entity.Partner;
import com.utils.DownloadUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/test")
public class ExportController {

	@Autowired
	private ObjectMapper objectMapper;

	// 定义导出excel名称
	private static final String EXCEL_NAME = "TestExcel.xlsx";
	// 定义Sheet名称
	private static final String SHEET_NAME = "测试";
	// 定义标题行
	private static final String[] COLUME_NAME_ARR = { "编号", "姓名", "手机", "最高学历", "国家地区", "护照号", "籍贯", "生日", "属相", "入职时间", "离职类型", "离职原因",
			"离职时间" };
	// 日期样式如yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss
	private static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private static SXSSFSheet sheet = null;
	private static Row row = null;
	private static Cell cell = null;

	// 表头字号, 列宽, 行高 尺寸, 内容字号初始化设置
	private static final int[] CONTENT_SIZE = { 13, 13, 18, 12 };

	// 内容字体设置
	private static final String FONT_SET = "宋体";

	// 单表sheet数据容量
	private static final int CAPACITY = 100 * 10000;

	// 测试数据量
	private static final int TEST_CAPACITY = 1500000;

	/**
	 * 当月xxx报表导出--百万以上
	 */
	@PostMapping("export")
	public void export(@RequestBody String jsonStr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		exportData(jsonStr, Partner.class, request, response);
	}

	//@RequestMapping(value = "/export/{month}", method = RequestMethod.GET)
	@SuppressWarnings("unchecked")
	public <E> void exportData(String jsonStr, Class<E> clz, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, clz);
		List<E> list = objectMapper.readValue(new String(jsonStr.getBytes("ISO-8859-1"), "UTF-8"), type);

		/** 获取报表数据 */
		Partner entity = new Partner();
		entity.setUserId(111000);
		entity.setUsername("老李");
		entity.setMobile("15566667777");
		entity.setEducation("senior");
		entity.setNationalArea("china");
		entity.setPassportNo("5637");
		entity.setNativePlace("GuangDong");
		entity.setZodiac("abc");
		entity.setTimeOfEntry("08:30");
		entity.setTypeOfTurnover(1);
		entity.setReasonsForLeaving("个人发展");
		entity.setResignationTime("2020-03-15");
		for (int i = 4; i <= TEST_CAPACITY; i++) {
			Partner employeeReportResult = (Partner) entity.clone();
			employeeReportResult.setUserId(i);
			list.add((E) employeeReportResult);
		}

		/** 构造Excel */
		//创建工作簿(内存中对象数量上限值200)
		SXSSFWorkbook sWorkBook = new SXSSFWorkbook(200);
		sheet = sWorkBook.createSheet(SHEET_NAME);

		/*//初始化Sheet
		SXSSFSheet sheet = sWorkBook.createSheet(SHEET_NAME);
		
		//设置标题样式和列名
		CellStyle headStyle = sWorkBook.createCellStyle(); //表头
		Font headFont = sWorkBook.createFont(); //表头字体
		headFont.setBold(true);
		headFont.setFontHeightInPoints((short) 12); //字号
		headStyle.setFont(headFont);
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		//水平居中
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER); //垂直居中
		Row row = sheet.createRow(0);
		int titleIndex = 0;
		Cell cell;
		for (String columnName : COLUME_NAME_ARR) {
			cell = row.createCell(titleIndex++);
			cell.setCellValue(columnName);
			cell.setCellStyle(headStyle);
		}
		
		*//** 设置公共样式 *//*
						CellStyle style = sWorkBook.createCellStyle();
						style.setAlignment(HorizontalAlignment.CENTER); //居中
						style.setVerticalAlignment(VerticalAlignment.CENTER); //垂直居中
						//创建字体对象,设置字体与字号
						Font font = sWorkBook.createFont();
						font.setFontName("宋体");
						font.setFontHeightInPoints((short) 12);
						style.setFont(font);
						//列宽与行高
						sheet.setDefaultColumnWidth(13);
						row.setHeightInPoints(18);*/
		//Integer defineWidth = null;

		//填充数据
		/*int rowIndex = 1;
		if (list != null && list.size() > 0) {
			for (E e : list) {
				row = sheet.createRow(rowIndex++); //创建行
				int i = 0;
				createCellByRow(style, sheet, row, i++, defineWidth, employeeReportResult.getUserId());
				createCellByRow(style, sheet, row, i++, defineWidth, employeeReportResult.getUsername());
				createCellByRow(style, sheet, row, i++, defineWidth, employeeReportResult.getMobile());
				createCellByRow(style, sheet, row, i++, defineWidth, employeeReportResult.getTheHighestDegreeOfEducation());
				createCellByRow(style, sheet, row, i++, defineWidth, employeeReportResult.getNationalArea());
				createCellByRow(style, sheet, row, i++, defineWidth, employeeReportResult.getPassportNo());
				createCellByRow(style, sheet, row, i++, defineWidth, employeeReportResult.getNativePlace());
				createCellByRow(style, sheet, row, i++, defineWidth, employeeReportResult.getBirthday());
				createCellByRow(style, sheet, row, i++, defineWidth, employeeReportResult.getZodiac());
				createCellByRow(style, sheet, row, i++, defineWidth, employeeReportResult.getTimeOfEntry());
				createCellByRow(style, sheet, row, i++, defineWidth, employeeReportResult.getTypeOfTurnover());
				createCellByRow(style, sheet, row, i++, defineWidth, employeeReportResult.getReasonsForLeaving());
				createCellByRow(style, sheet, row, i++, defineWidth, employeeReportResult.getResignationTime());
			}
		}*/

		//写入数据  
		E e; //实体类  
		Field[] fields; //属性数组  
		Field field; //属性  
		Object value; //属性值  
		//int rowIndex = 1;
		int sheetIndex = 0;

		sheet = sheetFormat(sWorkBook, sheet);
		CellStyle cellStyle = cellFormat(sWorkBook);

		for (int i = 0; i < list.size(); i++) {
			if (i != 0 && i % CAPACITY == 0) {
				sheet = sWorkBook.createSheet(SHEET_NAME + sheetIndex);
				sheet = sheetFormat(sWorkBook, sheet);
				sheetIndex++;
			}

			row = sheet.createRow((i + 1) - (sheetIndex * CAPACITY));

			e = (E) list.get(i);
			fields = e.getClass().getDeclaredFields();
			int columnNumber = 0;
			for (int j = 0; j < fields.length; j++) {
				field = fields[j];
				cell = row.createCell(columnNumber);
				cell.setCellStyle(cellStyle);

				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				// 获取属性值
				value = field.get(e);
				if (value == null) {
				} else if (value instanceof Integer) {
					Integer cellValueDb = (Integer) value;
					cell.setCellValue(cellValueDb);
				} else if (value instanceof Date) {
					cell.setCellValue(formatter.format((Date) value));
				} else {
					cell.setCellValue(value.toString());
				}
				/* // 自动列宽--数据量太大, 设置自动列宽将会非常耗费资源, 甚至无法响应, 因此这里不做设置!
				sheet.trackColumnForAutoSizing(columnNumber);
				sheet.autoSizeColumn(columnNumber);
				sheet.setColumnWidth(columnNumber, sheet.getColumnWidth(columnNumber) * 16 / 10);*/

				columnNumber++;
			}

		}

		/** 下载 */
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		sWorkBook.write(outputStream);
		sWorkBook.close();
		new DownloadUtils().download(outputStream, response, EXCEL_NAME);
		log.info(">>>数据导出成功!共{}条>>>", list.size());
	}

	/**
	 * sheet设置
	 */
	public SXSSFSheet sheetFormat(SXSSFWorkbook sWorkBook, SXSSFSheet sheet) {

		//设置标题样式和列名
		CellStyle headStyle = sWorkBook.createCellStyle(); //表头
		Font headFont = sWorkBook.createFont(); //表头字体
		headFont.setBold(true);
		headFont.setFontHeightInPoints((short) CONTENT_SIZE[0]); //表头字号
		headStyle.setFont(headFont);
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		//水平居中
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER); //垂直居中
		row = sheet.createRow(0);
		int titleIndex = 0;
		for (String columnName : COLUME_NAME_ARR) {
			cell = row.createCell(titleIndex++);
			cell.setCellValue(columnName);
			cell.setCellStyle(headStyle);
		}

		//列宽与行高
		sheet.setDefaultColumnWidth(CONTENT_SIZE[1]);
		row.setHeightInPoints(CONTENT_SIZE[2]);
		return sheet;
	}

	/**
	 * cell设置
	 */
	public CellStyle cellFormat(SXSSFWorkbook sWorkBook) {
		/** 设置公共样式 */
		CellStyle cellStyle = sWorkBook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER); //居中
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); //垂直居中
		//创建字体对象,设置内容字体与字号
		Font font = sWorkBook.createFont();
		font.setFontName(FONT_SET);
		font.setFontHeightInPoints((short) CONTENT_SIZE[3]);
		cellStyle.setFont(font);
		return cellStyle;
	}

	/**
	 * 设置样式(大数据中不使用)
	 */
	public void createCellByRow(CellStyle style, SXSSFSheet sheet, Row row, int columnNumber, Integer defineWidth, Object cellValue) {
		//创建单元格并设置值和样式
		Cell cell = row.createCell(columnNumber);

		if (cellValue instanceof Integer) {
			Integer cellValueDb = (Integer) cellValue;
			cell.setCellValue(cellValueDb);
		} else if (cellValue instanceof Date) {
			Date cellValueDb = (Date) cellValue;
			// 其它格式使用正则配合进行逻辑处理
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
			String formatDate = sdf.format(cellValueDb);
			cell.setCellValue(formatDate);
		} else {
			cell.setCellValue((String) cellValue);
		}

		cell.setCellStyle(style);
		// 自动列宽
		sheet.trackColumnForAutoSizing(columnNumber);
		sheet.autoSizeColumn(columnNumber);
		sheet.setColumnWidth(columnNumber, sheet.getColumnWidth(columnNumber) * 16 / 10);

		//个性化设置指定列列宽
		if (defineWidth != null) {
			sheet.setColumnWidth(columnNumber, defineWidth);
		}
	}

}
