package gov.gsa.dcoi.dto;

public class ServerInformationDto {

	private Integer dataCenterInventoryId;
	private Double serverUtilization;
	private Integer mainframeCount;
	private Integer windowsServerCount;
	private Integer hpcClusterCount;
	private Integer otherServerCount;
	private Integer virtualHostCount;
	private Integer virtualOSCount;
	private Double storageTotal;
	private Double storageUsed;

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

	public Integer getMainframeCount() {
		return mainframeCount;
	}

	public void setMainframeCount(Integer mainframeCount) {
		this.mainframeCount = mainframeCount;
	}

	public Integer getWindowsServerCount() {
		return windowsServerCount;
	}

	public void setWindowsServerCount(Integer windowsServerCount) {
		this.windowsServerCount = windowsServerCount;
	}

	public Integer getHpcClusterCount() {
		return hpcClusterCount;
	}

	public void setHpcClusterCount(Integer hpcClusterCount) {
		this.hpcClusterCount = hpcClusterCount;
	}

	public Integer getOtherServerCount() {
		return otherServerCount;
	}

	public void setOtherServerCount(Integer otherServerCount) {
		this.otherServerCount = otherServerCount;
	}

	public Integer getVirtualHostCount() {
		return virtualHostCount;
	}

	public void setVirtualHostCount(Integer virtualHostCount) {
		this.virtualHostCount = virtualHostCount;
	}

	public Integer getVirtualOSCount() {
		return virtualOSCount;
	}

	public void setVirtualOSCount(Integer virtualOSCount) {
		this.virtualOSCount = virtualOSCount;
	}

	public Double getStorageTotal() {
		return storageTotal;
	}

	public void setStorageTotal(Double storageTotal) {
		this.storageTotal = storageTotal;
	}

	public Double getStorageUsed() {
		return storageUsed;
	}

	public void setStorageUsed(Double storageUsed) {
		this.storageUsed = storageUsed;
	}

}
