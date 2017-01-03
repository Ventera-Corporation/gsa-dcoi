package gov.gsa.dcoi.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.opencsv.CSVWriter;

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
public class DcoiCSVWriter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CSVWriter.class);

	/**
	 * Export search/final report results, as well as cost model reports
	 * 
	 * @param sheetTitles
	 * @param searchResultsMap
	 * @return
	 */
	public byte[] exportReportResults(Map<String[], List<List<String>>> exportResultsMap) {
		CSVWriter writer = null;
		ByteArrayOutputStream bos = null;
		File tempFile = new File("tempCsv.csv");
		try {
			writer = new CSVWriter(new FileWriter(tempFile));
			for (Map.Entry<String[], List<List<String>>> headersAndExportData : exportResultsMap.entrySet()) {
				writer.writeNext(headersAndExportData.getKey());
				for (List<String> resultRow : headersAndExportData.getValue()) {
					String[] row = resultRow.toArray(new String[resultRow.size()]);
					writer.writeNext(row);
				}
			}
			return Files.readAllBytes(tempFile.toPath());
		} catch (IOException ioex) {
			LOGGER.error(ioex.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception Creating CSV file: " + ioex.getMessage());
		} finally {
			IOUtils.closeQuietly(bos);
			IOUtils.closeQuietly(writer);
			tempFile.delete();
		}
	}

	private String getNullSafeString(String value) {
		if (value == null || value.isEmpty()) {
			return "NONE";
		}
		return value;
	}

}
