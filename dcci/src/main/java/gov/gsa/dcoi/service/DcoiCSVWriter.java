package gov.gsa.dcoi.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
	 * @param exportResultsMap
	 * @return
	 */
	public byte[] exportReportResults(Map<String[], List<List<String>>> exportResultsMap) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			for (Map.Entry<String[], List<List<String>>> headersAndExportData : exportResultsMap.entrySet()) {
				for (int i = 0; i < headersAndExportData.getKey().length; i++) {
					bos.write((headersAndExportData.getKey()[i] + ",").getBytes());
				}
				bos.write("\n".getBytes());
				for (List<String> resultRow : headersAndExportData.getValue()) {
					String[] row = resultRow.toArray(new String[resultRow.size()]);
					for (int i = 0; i < row.length; i++) {
						bos.write((row[i] + ",").getBytes());
					}
					bos.write("\n".getBytes());
				}
			}

			return bos.toByteArray();
		} catch (IOException ioex) {
			LOGGER.error(ioex.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception Creating CSV file: " + ioex.getMessage());
		} finally {
			IOUtils.closeQuietly(bos);
		}
	}

}
