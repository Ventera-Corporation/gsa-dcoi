package gov.gsa.dcci.entity;

import javax.persistence.Entity;

@Entity
public class DataCenter {
	
	private int dataCenterId;
	private String dataCenterName;
	private String state;
	private String city;
	private String region;
	
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
	
	public String getState(){
		return state;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	public String getRegion(){
		return region;
	}
	
	public void setRegion(String region){
		this.region = region;
	}
	
	

}
