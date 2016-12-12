package gov.gsa.dcoi.refValueEntity;

/**
 * Value object class to hold id's and value's for reference value list items
 * 
 * @author sgonthier
 *
 */
public class GenericReferenceValueObject {

	private int id;
	private String value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}