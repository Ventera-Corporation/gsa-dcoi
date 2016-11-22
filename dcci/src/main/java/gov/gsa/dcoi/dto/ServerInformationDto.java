package gov.gsa.dcoi.dto;

public class ServerInformationDto {
	
	private int dataCenterInventoryId;
	private double serverUtilization;
	private int mainframeCount;
	private int windowsServerCount;
	private int hpcClusterCount;
	private int otherServerCount;
	private int virtualHostCount;
	private int virtualOSCount;
	private double storageTotal;
	private double storageUsed;
	
	public double getServerUtilization() {
		return serverUtilization;
	}
	public void setServerUtilization(double serverUtilization) {
		this.serverUtilization = serverUtilization;
	}
	public int getMainframeCount() {
		return mainframeCount;
	}
	public void setMainframeCount(int mainframeCount) {
		this.mainframeCount = mainframeCount;
	}
	public int getWindowsServerCount() {
		return windowsServerCount;
	}
	public void setWindowsServerCount(int windowsServerCount) {
		this.windowsServerCount = windowsServerCount;
	}
	public int getHpcClusterCount() {
		return hpcClusterCount;
	}
	public void setHpcClusterCount(int hpcClusterCount) {
		this.hpcClusterCount = hpcClusterCount;
	}
	public int getOtherServerCount() {
		return otherServerCount;
	}
	public void setOtherServerCount(int otherServerCount) {
		this.otherServerCount = otherServerCount;
	}
	public int getVirtualHostCount() {
		return virtualHostCount;
	}
	public void setVirtualHostCount(int virtualHostCount) {
		this.virtualHostCount = virtualHostCount;
	}
	public int getVirtualOSCount() {
		return virtualOSCount;
	}
	public void setVirtualOSCount(int virtualOSCount) {
		this.virtualOSCount = virtualOSCount;
	}
	public double getStorageTotal() {
		return storageTotal;
	}
	public void setStorageTotal(double storageTotal) {
		this.storageTotal = storageTotal;
	}
	public double getStorageUsed() {
		return storageUsed;
	}
	public void setStorageUsed(double storageUsed) {
		this.storageUsed = storageUsed;
	}
	public int getDataCenterInventoryId() {
		return dataCenterInventoryId;
	}
	public void setDataCenterInventoryId(int dataCenterInventoryId) {
		this.dataCenterInventoryId = dataCenterInventoryId;
	}

}
