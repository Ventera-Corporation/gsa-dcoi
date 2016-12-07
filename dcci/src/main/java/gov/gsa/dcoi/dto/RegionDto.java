package gov.gsa.dcoi.dto;

import java.util.List;

/**
 * Dto that holds information for each region. Data centers
 * are displayed/grouped on the front by the region that they belong to. Thus,
 * each region contains a list of dataCenters that are a part of that region.
 * @author sgonthier
 *
 */
public class RegionDto {

	private Integer regionId;
	private String name;
	private String code;
	private List<DataCenterDto> dataCenters;

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
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
