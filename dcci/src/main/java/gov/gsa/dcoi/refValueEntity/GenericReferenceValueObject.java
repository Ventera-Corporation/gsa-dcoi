package gov.gsa.dcoi.refValueEntity;

/**
 * Value object class to hold id's and value's for reference value list items
 * 
 * @author sgonthier
 *
 */
public class GenericReferenceValueObject {

	private Integer id;
	private String value;
	private String code;
	private Integer activeFlag;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
