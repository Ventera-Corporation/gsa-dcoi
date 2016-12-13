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
	private Double serverUtilization;
	@Min(0)
	private Integer totalMainframes;
	@Min(0)
	private Integer totalWindowsServers;
	@Min(0)
	private Integer totalHPCClusterNodes;
	@Min(0)
	private Integer totalOtherServers;
	@Min(0)
	private Integer totalVirtualHosts;
	@Min(0)
	private Integer totalVirtualOS;
	@DecimalMin("0.00")
	private Double totalStorage;
	@DecimalMin("0.00")
	private Double usedStorage;

	public Integer getDataCenterInventoryId() {
		return dataCenterInventoryId;
	}

	public void setDataCenterInventoryId(Integer dataCenterInventoryId) {
		this.dataCenterInventoryId = dataCenterInventoryId;
	}

	public Double getServerUtilization() {
		return serverUtilization;
	}

	public void setServerUtilization(Double serverUtilization) {
		this.serverUtilization = serverUtilization;
	}

	public Integer getTotalMainframes() {
		return totalMainframes;
	}

	public void setTotalMainframes(Integer totalMainframes) {
		this.totalMainframes = totalMainframes;
	}

	public Integer getTotalWindowsServers() {
		return totalWindowsServers;
	}

	public void setTotalWindowsServers(Integer totalWindowsServers) {
		this.totalWindowsServers = totalWindowsServers;
	}

	public Integer getTotalHPCClusterNodes() {
		return totalHPCClusterNodes;
	}

	public void setTotalHPCClusterNodes(Integer totalHPCClusterNodes) {
		this.totalHPCClusterNodes = totalHPCClusterNodes;
	}

	public Integer getTotalOtherServers() {
		return totalOtherServers;
	}

	public void setTotalOtherServers(Integer totalOtherServers) {
		this.totalOtherServers = totalOtherServers;
	}

	public Integer getTotalVirtualHosts() {
		return totalVirtualHosts;
	}

	public void setTotalVirtualHosts(Integer totalVirtualHosts) {
		this.totalVirtualHosts = totalVirtualHosts;
	}

	public Integer getTotalVirtualOS() {
		return totalVirtualOS;
	}

	public void setTotalVirtualOS(Integer totalVirtualOS) {
		this.totalVirtualOS = totalVirtualOS;
	}

	public Double getTotalStorage() {
		return totalStorage;
	}

	public void setTotalStorage(Double totalStorage) {
		this.totalStorage = totalStorage;
	}

	public Double getUsedStorage() {
		return usedStorage;
	}

	public void setUsedStorage(Double usedStorage) {
		this.usedStorage = usedStorage;
	}

}
