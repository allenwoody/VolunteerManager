package com.allen.core.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class IncCordInfoPOI {
	private String path = "";

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public IncCordInfoPOI(String path){
		this.path = path;
	}
	
	
	public String exameIfExcelFormatRight() throws Exception{
		String type = this.path.substring(this.path.lastIndexOf(".") + 1, this.path.length());
		if(!type.equals("xlsx")){
			return "请导入07以上版本的excel文件";
		}
		
		String[] titles =  {"项目名称","活动内容","开始时间","结束时间","预约电话","卡号","保单号"};
		
		FileInputStream workBookStream = new FileInputStream(this.path);
		if(workBookStream==null || titles==null){
			return "导入出错，请尝试重新上传导入文件。";
		}

		XSSFWorkbook xssfWorkbook=new XSSFWorkbook(workBookStream);

		
		if (xssfWorkbook.getNumberOfSheets()<=0) {
			return "excel缺少工作表，请检查上传的导入文件。";
		}else{//默认取第一个excel的标题头数据
			List<String> excelTitles=null;
			XSSFSheet xssfSheet=xssfWorkbook.getSheetAt(0);
			
			//得到上传文件的表头数据
			excelTitles=gainColNameList(xssfSheet);
			//原始表头
			List<String> titlesList=new ArrayList<String>();
			//转成list形式用来比较
			for (String string : titles) {
				titlesList.add(string);
			}
			if(!excelTitles.equals(titlesList)){
				return "导入文件的工作表标题行和模板文件标题行不一致，请重新下载模板文件，装载数据后尝试再次导入。";
			}
			
		}
		
		return "";
	}
	
	/**
	 * 得到sheet中第一行的值，存在List中
	 * @return
	 * @throws POIException
	 */
	public static List<String> gainColNameList(XSSFSheet xssfSheet) throws Exception{
		if(xssfSheet == null){
			return null;
		}
 		List<String> titles = new ArrayList<String>();
 		
 		//第一行代表表头
		XSSFRow xssfRow=xssfSheet.getRow(0);
		
 		XSSFCell cell;
 		
 		if(xssfRow!=null){
 			for(int i=0;xssfRow.getCell((short) i)!=null;i++){
 				cell=xssfRow.getCell((short) i);
 				titles.add(getStringCellValue(cell));
 			}
 		}
 		return titles;
	}
	
	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * @param cell Excel单元格
	 * @return String 单元格数据内容
	 */
	public static String getStringCellValue(XSSFCell xssfRow) {
		if(xssfRow==null){
			return "";
		}
		
		String strCell = "";
		switch (xssfRow.getCellType()) {
		case XSSFCell.CELL_TYPE_STRING:
			strCell = xssfRow.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(xssfRow.getNumericCellValue());
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(xssfRow.getBooleanCellValue());
			break;
		case XSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell == null || strCell.equals("")) {
			return "";
		}
		return strCell.replaceAll(" ", "");
	}
	
	
	
	public List<Map> gainDateForMapList() throws Exception {
		FileInputStream workBookStream = new FileInputStream(this.path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(workBookStream);
		
		List ls = new ArrayList<Map>();
		if (xssfWorkbook.getSheetAt(0) == null) {
			return ls;
		}else {
			Map row;
			XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
			List<String> iter = this.gainColNameList2(xssfWorkbook);
			for (int i = 1; sheet.getRow(i) != null ; i++) {
				row = new LinkedHashMap<String, Object>();
				int isNull = 0;
				for(int j = 0; j < iter.size(); j++) {
					XSSFCell cell = sheet.getRow(i).getCell((short) j);
					if (cell == null) {
						row.put(iter.get(j), null);
						isNull++;
						continue;
					}
					Object val = this.format2(cell);
					if (val.equals("#BLANK")) {
						
						val = "";
					} else if (val.equals("#ERROR")) {
						isNull++;
						val = null;
					}
					row.put(iter.get(j), val);
				}
				if (isNull > 5) {
					isNull--;
					isNull++;
				}
				if (isNull < iter.size()) {
					ls.add(row);
				}
			}
			return ls;
		}
	}
	
	private List<String> gainColNameList2(XSSFWorkbook xssfWorkbook) throws Exception{
 		List<String> ls = new ArrayList<String>();
		if (xssfWorkbook.getSheetAt(0) == null) {
			return ls;
		}else {
			XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
				for (int j = 0; sheet.getRow(0).getCell((short) j) != null; j++) {
				XSSFCell cell = sheet.getRow(0).getCell((short) j);
				ls.add( String.valueOf(this.format2(cell)).toUpperCase() );
			}
			return ls;
		}
	}
	
	protected Object format2(XSSFCell cell) throws Exception {
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_BLANK:
			return "#BLANK";
		case XSSFCell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
		case XSSFCell.CELL_TYPE_ERROR:
			return "#ERROR";
		case XSSFCell.CELL_TYPE_FORMULA:
			return cell.getCellFormula().trim();
		case XSSFCell.CELL_TYPE_NUMERIC:
			if (this.checkStyle(cell)) {
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //dd-MMM-yyyy
				return sdf.format(cell.getDateCellValue());
			} else {
				return (int) cell.getNumericCellValue();
			}
		case XSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue().trim();
		default:
			throw new Exception("Unknown Cell Type: " + cell.getCellType());
		}
	}
	
	/**
	 * 检查单元格的类型是否属于日期型。
	 * @param cell
	 * @return
	 */
	private boolean checkStyle(XSSFCell cell) {
		if (XSSFDateUtil.isCellDateFormatted(cell)) {
			return true;
		}
		switch (cell.getCellStyle().getDataFormat()) { //  自定义类型好难处理阿。。。
		case 188:
		case 184:
		//case 176:
			return true;
		default:
			return false;
		}
	}
}
