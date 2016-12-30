package gov.gsa.dcoi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class to hold the basic/static information about a data center,
 * including its address, region/location and name
 * 
 * @author sgonthier
 *
 */
@Entity
public class DataCenter {

	@Id
	@GeneratedValue
	private Integer dataCenterId;

	private String dcoiDataCenterId;

	public Integer getDataCenterId() {
		return dataCenterId;
	}

	public void setDataCenterId(Integer dataCenterId) {
		this.dataCenterId = dataCenterId;
	}

	public String getDcoiDataCenterId() {
		return dcoiDataCenterId;
	}

	public void setDcoiDataCenterId(String dcoiDataCenterId) {
		this.dcoiDataCenterId = dcoiDataCenterId;
	}

}
