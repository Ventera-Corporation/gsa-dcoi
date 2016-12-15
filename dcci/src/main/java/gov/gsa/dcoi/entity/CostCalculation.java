package gov.gsa.dcoi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity object to hold all the savings and 
 * avoidance information based on the cost calculation
 * done by the user in the front end
 * @author sgonthier
 *
 */
@Table(name = "data_center_cost")
@Entity
public class CostCalculation {
	
	@Id
	@GeneratedValue
	private Long dataCenterCostId;
	private Long dataCenterQuarterId;
	@Column(name = "server_amount")
	private Double serverCost;
	@Column(name = "storage_amount")
	private Double storageCost;
	@Column(name = "other_amount")
	private Double miscellaneous;
	@Column(name = "total_amount")
	private Double total;
	private Double savingsAmount;
	@Column(name = "avoidance_year_1")
	private Integer avoidanceYear1;
	@Column(name = "avoidance_amount_year_1")
	private Double avoidanceAmountYear1;
	@Column(name = "avoidance_year_2")
	private Integer avoidanceYear2;
	@Column(name = "avoidance_amount_year_2")
	private Double avoidanceAmountYear2;
	@Column(name = "avoidance_year_3")
	private Integer avoidanceYear3;
	@Column(name = "avoidance_amount_year_3")
	private Double avoidanceAmountYear3;

	public Long getDataCenterCostId() {
		return dataCenterCostId;
	}

	public void setDataCenterCostId(Long dataCenterCostId) {
		this.dataCenterCostId = dataCenterCostId;
	}

	public Long getDataCenterQuarterId() {
		return dataCenterQuarterId;
	}

	public void setDataCenterQuarterId(Long dataCenterQuarterId) {
		this.dataCenterQuarterId = dataCenterQuarterId;
	}

	public Double getServerCost() {
		return serverCost;
	}

	public void setServerCost(Double serverCost) {
		this.serverCost = serverCost;
	}

	public Double getStorageCost() {
		return storageCost;
	}

	public void setStorageCost(Double storageCost) {
		this.storageCost = storageCost;
	}

	public Double getMiscellaneous() {
		return miscellaneous;
	}

	public void setMiscellaneous(Double miscellaneous) {
		this.miscellaneous = miscellaneous;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getSavingsAmount() {
		return savingsAmount;
	}

	public void setSavingsAmount(Double savingsAmount) {
		this.savingsAmount = savingsAmount;
	}

	public Integer getAvoidanceYear1() {
		return avoidanceYear1;
	}

	public void setAvoidanceYear1(Integer avoidanceYear1) {
		this.avoidanceYear1 = avoidanceYear1;
	}

	public Double getAvoidanceAmountYear1() {
		return avoidanceAmountYear1;
	}

	public void setAvoidanceAmountYear1(Double avoidanceAmountYear1) {
		this.avoidanceAmountYear1 = avoidanceAmountYear1;
	}

	public Integer getAvoidanceYear2() {
		return avoidanceYear2;
	}

	public void setAvoidanceYear2(Integer avoidanceYear2) {
		this.avoidanceYear2 = avoidanceYear2;
	}

	public Double getAvoidanceAmountYear2() {
		return avoidanceAmountYear2;
	}

	public void setAvoidanceAmountYear2(Double avoidanceAmountYear2) {
		this.avoidanceAmountYear2 = avoidanceAmountYear2;
	}

	public Integer getAvoidanceYear3() {
		return avoidanceYear3;
	}

	public void setAvoidanceYear3(Integer avoidanceYear3) {
		this.avoidanceYear3 = avoidanceYear3;
	}

	public Double getAvoidanceAmountYear3() {
		return avoidanceAmountYear3;
	}

	public void setAvoidanceAmountYear3(Double avoidanceAmountYear3) {
		this.avoidanceAmountYear3 = avoidanceAmountYear3;
	}

}
