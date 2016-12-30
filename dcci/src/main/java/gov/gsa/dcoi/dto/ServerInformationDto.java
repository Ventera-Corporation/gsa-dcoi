package gov.gsa.dcoi.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

/**
 * Dto that holds information regarding the data center servers. This
 * information is held at the field office level
 * 
 * @author sgonthier
 *
 */
public class ServerInformationDto {

	private Integer dataCenterInventoryId;
	@DecimalMin("0.00")
	@DecimalMax("1.00")
	private String serverUtilization;
	@Min(0)
	private String totalMainframes;
	@Min(0)
	private String totalWindowsServers;
	@Min(0)
	private String totalHPCClusterNodes;
	@Min(0)
	private String totalOtherServers;
	@Min(0)
	private String totalVirtualHosts;
	@Min(0)
	private String totalVirtualOS;
	@DecimalMin("0.00")
	private String totalStorage;
	@DecimalMin("0.00")
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
