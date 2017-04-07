package gov.gsa.dcoi.refValueEntity;

/**
 * Constants class to represent id's for some of the reference values
 */
public interface ReferenceValueConstants {

	public static final Integer ZERO = Integer.valueOf(0);
	public static final Integer ONE = Integer.valueOf(1);
	public static final Integer TWO = Integer.valueOf(2);
	public static final Integer THREE = Integer.valueOf(3);
	public static final Integer FOUR = Integer.valueOf(4);
	public static final Integer FIVE = Integer.valueOf(5);
	public static final Integer SIX = Integer.valueOf(6);
	public static final Integer SEVEN = Integer.valueOf(7);
	public static final Integer EIGHT = Integer.valueOf(8);
	public static final Integer TEN = Integer.valueOf(10);

	public static final Integer YES = ONE;
	public static final Integer NO = TWO;

	public static final Integer OCIO_ID = SEVEN;

	// Record Validity
	public static final Integer INVALID_FACILITY = ONE;
	public static final Integer VALID_FACILITY = TWO;
	// public static final Integer ADDED_FACILITY = THREE;

	// Record Status
	public static final Integer EXISTING_FACILITY = ONE;
	public static final Integer NEW_FACILITY = TWO;

	// Closing stage
	public static final Integer CLOSED = TWO;
	public static final Integer CONSIDERING = THREE;
	public static final Integer NOT_CLOSING = EIGHT;

	// Ownership Type
	public static final Integer AGENCY_OWNED = ONE;
	public static final Integer CLOUD_PROVIDER_OWNERSHIP_TYPE = TEN;

	// Data Center Tier
	public static final Integer OTHER_ROOM_TIER = ONE;
	public static final Integer SERVER_ROOM_TIER = TWO;
	public static final Integer TIER_1 = THREE;
	public static final Integer TIER_2 = FOUR;
	public static final Integer TIER_3 = FIVE;
	public static final Integer TIER_4 = SIX;
	public static final Integer UNKNOWN_TIER = SEVEN;
	public static final Integer DC_TIER_CLOUD_PROVIDER = EIGHT;

	// User roles
	public static final Integer ADMIN_ROLE = ONE;
	public static final Integer FACILITY_ROLE = TWO;
	public static final Integer SERVER_ROLE = THREE;

}
