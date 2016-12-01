package gov.gsa.dcoi.dto;

import java.util.List;

public class RegionDto {
	
	private Integer id;
	private String name;
	private String code;
	private List<DataCenterDto> dataCenters;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<DataCenterDto> getDataCenters() {
		return dataCenters;
	}
	public void setDataCenters(List<DataCenterDto> dataCenters) {
		this.dataCenters = dataCenters;
	}

}
