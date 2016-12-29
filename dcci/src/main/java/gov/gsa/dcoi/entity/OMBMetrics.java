package gov.gsa.dcoi.entity;

/**
 * Pojo class to hold the read-only other calculations data
 * 
 * @author sgonthier
 *
 */
public class OMBMetrics {

	private String fiscalYear;
	private String fiscalQuarter;
	private String energyMetering;
	private String automatedMonitoring;
	private String facilityUtilization;

	private static final String PERCENTAGE = "%";

	public String getEnergyMetering() {
		return this.energyMetering;
	}

	public void setEnergyMetering(Integer energyMetering) {
		this.energyMetering = getStringValue(energyMetering) + PERCENTAGE;
	}

	public String getAutomatedMonitoring() {
		return this.automatedMonitoring;
	}

	public void setAutomatedMonitoring(Integer automatedMonitoring) {
		this.automatedMonitoring = getStringValue(automatedMonitoring) + PERCENTAGE;
	}

	public String getFacilityUtilization() {
		return this.facilityUtilization;
	}

	public void setFacilityUtilization(Integer facilityUtilization) {
		this.facilityUtilization = getStringValue(facilityUtilization) + PERCENTAGE;
	}

	public String getFiscalYear() {
		return this.fiscalYear;
	}

	public void setFiscalYear(Integer fiscalYear) {
		this.fiscalYear = fiscalYear.toString();
	}

	public String getFiscalQuarter() {
		return this.fiscalQuarter;
	}

	public void setFiscalQuarter(String fiscalQuarter) {
		this.fiscalQuarter = fiscalQuarter;
	}

	/**
	 * Helper method to return string values of the calculated metrics
	 */
	private String getStringValue(Integer intValue) {
		return intValue.toString();
	}

}
