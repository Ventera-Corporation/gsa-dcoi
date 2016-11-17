package gov.gsa.dcoi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DataCenter {
	
	@Id
	private int dataCenterId;
	private String dataCenterName;
	private int dcoiDataCenterId;
	private String address;
	private String address2;
	private String city;
	private int zipCode;
	private int stateId;
	private int regionId;
	private int coutnryId;
	
	public int getDataCenterId(){
		return dataCenterId;
	}
	
	public void setDataCenterId(int dataCenterId){
		this.dataCenterId =  dataCenterId;
	}
	
	public String getDataCenterName(){
		return dataCenterName;
	}
	
	public void setDataCenterName(String dataCenterName){
		this.dataCenterName = dataCenterName;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setCity(String city){
		this.city = city;
	}

	public int getDcoiDataCenterId() {
		return dcoiDataCenterId;
	}

	public void setDcoiDataCenterId(int dcoiDataCenterId) {
		this.dcoiDataCenterId = dcoiDataCenterId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public int getCoutnryId() {
		return coutnryId;
	}

	public void setCoutnryId(int coutnryId) {
		this.coutnryId = coutnryId;
	}
	
	

}
