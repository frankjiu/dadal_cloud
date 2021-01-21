package com.modules.export2;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: QiuQiang
 * @Date: 2020-09-30
 */
@RestController
@Slf4j
public class ExportData {

    //定义导出excel名称
    private static final String EXCEL_NAME = "demo.xlsx";
    //定义Sheet名称
    private static final String SHEET_NAME = "s";
    //定义标题行
    private static final String[] COLUME_NAME_ARR = { "id", "cardName", "cardNumber" };
    private static Row row = null;
    private static Cell cell = null;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    ObjectMapper mapper;

    /**
     * 预警结果数据下载
     */
    @PostMapping("export")
    public void export(@RequestBody String demoVoJsonStr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Demo> list = new ArrayList<Demo>();
        for (int i = 1; i < 21000; i++) {
            Demo demo = new Demo().builder().id(i).cardName("中国银行" + i).cardNumber(99999999555L + i).build();
            list.add(demo);
            if (i % 2 == 0) {
                Demo newDemo = new Demo().builder().id(i).cardName("中国银行" + (i - 1)).cardNumber(99999999555L + i - 1).build();
                list.add(newDemo);
            }
        }
        String str = mapper.writeValueAsString(list);
        exportData(str, Demo.class, request, response);
    }

    public <E> void exportData(String jsonStr, Class<E> clz, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /** 获取数据 */
        JavaType type = mapper.getTypeFactory().constructParametricType(List.class, clz);
        List<E> list = mapper.readValue(new String(jsonStr.getBytes(DownloadUtils.CODE_UTF8), DownloadUtils.CODE_UTF8), type);

        /** 构造Excel */
        //创建工作簿(内存中对象数量上限值200)
        SXSSFWorkbook sWorkBook = new SXSSFWorkbook(200);
        //初始化Sheet
        SXSSFSheet sheet = sWorkBook.createSheet(SHEET_NAME);

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
            if (i != 0 && i % DownloadUtils.CAPACITY == 0) {
                sheet = sWorkBook.createSheet(SHEET_NAME + sheetIndex);
                sheet = sheetFormat(sWorkBook, sheet);
                sheetIndex++;
            }

            row = sheet.createRow((i + 1) - (sheetIndex * DownloadUtils.CAPACITY));

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
                    cell.setCellValue(value.toString());
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
     * 将Long类型的时间戳转换成String类型的时间格式
     */
    public static String convertTimeToString(Long time) {
        Assert.notNull(time, "the param time is null!");
        String patternFirst = "yyyy-MM-ddTHH:mm:ss";
        String patternSecond = "yyyy-MM-dd";
        String patternThird = "HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFirst);
        return formatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.of("Asia/Shanghai")));
    }

    /**
     * 判断是否是yyyy-MM-dd日期格式
     */
    public static boolean isDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            format.setLenient(false);
            format.parse(strDate.replace("/", "-"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * sheet设置
     */
    public SXSSFSheet sheetFormat(SXSSFWorkbook sWorkBook, SXSSFSheet sheet) {

        //设置标题样式和列名
        CellStyle headStyle = sWorkBook.createCellStyle(); //表头
        Font headFont = sWorkBook.createFont(); //表头字体
        headFont.setBold(true);
        headFont.setFontHeightInPoints((short) DownloadUtils.CONTENT_SIZE[0]); //表头字号
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
        sheet.setDefaultColumnWidth(DownloadUtils.CONTENT_SIZE[1]);
        row.setHeightInPoints(DownloadUtils.CONTENT_SIZE[2]);
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
        font.setFontName(DownloadUtils.FONT_SET);
        font.setFontHeightInPoints((short) DownloadUtils.CONTENT_SIZE[3]);
        cellStyle.setFont(font);
        return cellStyle;
    }

}
