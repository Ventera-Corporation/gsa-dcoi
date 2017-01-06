package gov.gsa.dcoi.dto;

import javax.validation.constraints.Pattern;

import gov.gsa.dcoi.validator.DcoiDecimalMax;
import gov.gsa.dcoi.validator.DcoiDecimalMin;
import gov.gsa.dcoi.validator.DcoiMin;

/**
 * Dto that holds information regarding the data center servers. This
 * information is held at the field office level
 * 
 * @author sgonthier
 *
 */
public class ServerInformationDto {

	private Integer dataCenterInventoryId;
	@DcoiDecimalMin(0.00)
	@DcoiDecimalMax(1.00)
	@Pattern(regexp = "([0-9]?.[0-9][0-9]?|^$)")
	private String serverUtilization;
	@DcoiMin(0)
	private String totalMainframes;
	@DcoiMin(0)
	private String totalWindowsServers;
	@DcoiMin(0)
	private String totalHPCClusterNodes;
	@DcoiMin(0)
	private String totalOtherServers;
	@DcoiMin(0)
	private String totalVirtualHosts;
	@DcoiMin(0)
	private String totalVirtualOS;
	@DcoiDecimalMin(0.00)
	private String totalStorage;
	@DcoiDecimalMin(0.00)
	private String usedStorage;

	public Integer getDataCenterInventoryId() {
		return dataCenterInventoryId;
	}

	public void setDataCenterInventoryId(Integer dataCenterInventoryId) {
		this.dataCenterInventoryId = dataCenterInventoryId;
	}

	public String getServerUtilization() {
		return serverUtilization;
	}

	public void setServerUtilization(String serverUtilization) {
		this.serverUtilization = serverUtilization;
	}

	public String getTotalMainframes() {
		return totalMainframes;
	}

	public void setTotalMainframes(String totalMainframes) {
		this.totalMainframes = totalMainframes;
	}

	public String getTotalWindowsServers() {
		return totalWindowsServers;
	}

	public void setTotalWindowsServers(String totalWindowsServers) {
		this.totalWindowsServers = totalWindowsServers;
	}

	public String getTotalHPCClusterNodes() {
		return totalHPCClusterNodes;
	}

	public void setTotalHPCClusterNodes(String totalHPCClusterNodes) {
		this.totalHPCClusterNodes = totalHPCClusterNodes;
	}

	public String getTotalOtherServers() {
		return totalOtherServers;
	}

	public void setTotalOtherServers(String totalOtherServers) {
		this.totalOtherServers = totalOtherServers;
	}

	public String getTotalVirtualHosts() {
		return totalVirtualHosts;
	}

	public void setTotalVirtualHosts(String totalVirtualHosts) {
		this.totalVirtualHosts = totalVirtualHosts;
	}

	public String getTotalVirtualOS() {
		return totalVirtualOS;
	}

	public void setTotalVirtualOS(String totalVirtualOS) {
		this.totalVirtualOS = totalVirtualOS;
	}

	public String getTotalStorage() {
		return totalStorage;
	}

	public void setTotalStorage(String totalStorage) {
		this.totalStorage = totalStorage;
	}

	public String getUsedStorage() {
		return usedStorage;
	}

	public void setUsedStorage(String usedStorage) {
		this.usedStorage = usedStorage;
	}

}
