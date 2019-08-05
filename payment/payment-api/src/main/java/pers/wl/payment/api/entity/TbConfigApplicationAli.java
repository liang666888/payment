package pers.wl.payment.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tb_config_application_ali database table.
 * 
 */
@Entity
@Table(name="tb_config_application_ali")
@NamedQuery(name="TbConfigApplicationAli.findAll", query="SELECT t FROM TbConfigApplicationAli t")
public class TbConfigApplicationAli implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="serial_id")
	private String serialId;

	@Column(name="app_id")
	private String appId;

	@Column(name="config_ali_id")
	private String configAliId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="notify_url")
	private String notifyUrl;

	@Column(name="return_url")
	private String returnUrl;

	private String stat;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	private int weight;

	public TbConfigApplicationAli() {
	}

	public String getSerialId() {
		return this.serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getConfigAliId() {
		return this.configAliId;
	}

	public void setConfigAliId(String configAliId) {
		this.configAliId = configAliId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getNotifyUrl() {
		return this.notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getReturnUrl() {
		return this.returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getStat() {
		return this.stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}