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
 * The persistent class for the tb_config_ali database table.
 * 
 */
@Entity
@Table(name="tb_config_ali")
@NamedQuery(name="TbConfigAli.findAll", query="SELECT t FROM TbConfigAli t")
public class TbConfigAli implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="config_ali_id")
	private String configAliId;

	@Column(name="ali_app_id")
	private String aliAppId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	private String partner;

	@Column(name="private_key")
	private String privateKey;

	@Column(name="seller_id")
	private String sellerId;

	@Column(name="sign_type")
	private String signType;

	private String stat;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	public TbConfigAli() {
	}

	public String getConfigAliId() {
		return this.configAliId;
	}

	public void setConfigAliId(String configAliId) {
		this.configAliId = configAliId;
	}

	public String getAliAppId() {
		return this.aliAppId;
	}

	public void setAliAppId(String aliAppId) {
		this.aliAppId = aliAppId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPartner() {
		return this.partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getPrivateKey() {
		return this.privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getSignType() {
		return this.signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
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

}