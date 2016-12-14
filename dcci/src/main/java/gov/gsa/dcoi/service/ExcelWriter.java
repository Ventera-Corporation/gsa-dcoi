package gov.gsa.dcoi.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import gov.gsa.dcoi.DcoiExceptionHandler;

/**
 * Class to handle the writing of reports from the application out to an excel
 * workbook - returns as a byte array to be returned within a Blob on the front
 * end
 * 
 * @author sgonthier
 *
 */
@Component
public class ExcelWriter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelWriter.class);

	/**
	 * Export search/final report results, as well as cost model reports
	 * 
	 * @param sheetTitles
	 * @param searchResultsMap
	 * @return
	 */
	public byte[] exportReportResults(String[] sheetTitles, Map<String[], List<List<String>>> exportResultsMap) {
		HSSFWorkbook workbook = null;
		ByteArrayOutputStream bos = null;
		try {
			workbook = new HSSFWorkbook();
			int sheetNum = 0;
			for (Map.Entry<String[], List<List<String>>> headersAndExportData : exportResultsMap.entrySet()) {
				HSSFSheet sheet = null;
				if (sheetTitles == null) {
					sheet = workbook.createSheet("Results Sheet " + (sheetNum + 1));
				} else {
					sheet = workbook.createSheet(sheetTitles[sheetNum]);
				}
				sheetNum++;
				sheet.setFitToPage(true);

				// header row
				Row headerRow = sheet.createRow(0);
				headerRow.setHeightInPoints(40);
				int noOfColumns = 0;
				for (String header : headersAndExportData.getKey()) {
					Cell headerCell = headerRow.createCell(noOfColumns++);
					headerCell.setCellValue(header);
				}

				int rowIdx = 1;
				for (List<String> resultRow : headersAndExportData.getValue()) {
					Row row = sheet.createRow(rowIdx++);
					row.setHeightInPoints(25);
					int cellIdx = 0;
					for (String cellValue : resultRow) {
						Cell cell = row.createCell(cellIdx++);
						cell.setCellValue(getNullSafeString(cellValue));
					}
				}
				// autosize the column widths
				for (int idx = 0; idx < noOfColumns; idx++) {
					sheet.autoSizeColumn(idx);
				}
			}
			bos = new ByteArrayOutputStream();
			workbook.write(bos);
			return bos.toByteArray();
		} catch (IOException ioex) {
			LOGGER.error(ioex.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception Creating Excel Workbook: " + ioex.getMessage());
		} finally {
			IOUtils.closeQuietly(bos);
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}
	}

	private String getNullSafeString(String value) {
		if (value == null || value.isEmpty()) {
			return "NONE";
		}
		return value;
	}

}
