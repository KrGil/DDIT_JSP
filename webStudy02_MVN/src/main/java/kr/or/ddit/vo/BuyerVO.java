package kr.or.ddit.vo;

import java.io.Serializable;

public class BuyerVO implements Serializable{
	private String buyer_id;
	private String buyer_name;
	private String buyer_lgu;
	private String buyer_bank;
	private String buyer_bankno;
	private String buyer_bankname;
	private String buyer_zip;
	private String buyer_add1;
	private String buyer_add2;
	private String buyer_comtel;
	private String buyer_fax;
	private String buyer_mail;
	private String buyer_charger;
	private String buyer_telext;
	
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public String getBuyer_lgu() {
		return buyer_lgu;
	}
	public void setBuyer_lgu(String buyer_lgu) {
		this.buyer_lgu = buyer_lgu;
	}
	public String getBuyer_bank() {
		return buyer_bank;
	}
	public void setBuyer_bank(String buyer_bank) {
		this.buyer_bank = buyer_bank;
	}
	public String getBuyer_bankno() {
		return buyer_bankno;
	}
	public void setBuyer_bankno(String buyer_bankno) {
		this.buyer_bankno = buyer_bankno;
	}
	public String getBuyer_bankname() {
		return buyer_bankname;
	}
	public void setBuyer_bankname(String buyer_bankname) {
		this.buyer_bankname = buyer_bankname;
	}
	public String getBuyer_zip() {
		return buyer_zip;
	}
	public void setBuyer_zip(String buyer_zip) {
		this.buyer_zip = buyer_zip;
	}
	public String getBuyer_add1() {
		return buyer_add1;
	}
	public void setBuyer_add1(String buyer_add1) {
		this.buyer_add1 = buyer_add1;
	}
	public String getBuyer_add2() {
		return buyer_add2;
	}
	public void setBuyer_add2(String buyer_add2) {
		this.buyer_add2 = buyer_add2;
	}
	public String getBuyer_comtel() {
		return buyer_comtel;
	}
	public void setBuyer_comtel(String buyer_comtel) {
		this.buyer_comtel = buyer_comtel;
	}
	public String getBuyer_fax() {
		return buyer_fax;
	}
	public void setBuyer_fax(String buyer_fax) {
		this.buyer_fax = buyer_fax;
	}
	public String getBuyer_mail() {
		return buyer_mail;
	}
	public void setBuyer_mail(String buyer_mail) {
		this.buyer_mail = buyer_mail;
	}
	public String getBuyer_charger() {
		return buyer_charger;
	}
	public void setBuyer_charger(String buyer_charger) {
		this.buyer_charger = buyer_charger;
	}
	public String getBuyer_telext() {
		return buyer_telext;
	}
	public void setBuyer_telext(String buyer_telext) {
		this.buyer_telext = buyer_telext;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyer_id == null) ? 0 : buyer_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BuyerVO other = (BuyerVO) obj;
		if (buyer_id == null) {
			if (other.buyer_id != null)
				return false;
		} else if (!buyer_id.equals(other.buyer_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BuyerVO [buyer_id=" + buyer_id + ", buyer_name=" + buyer_name + ", buyer_lgu=" + buyer_lgu
				+ ", buyer_bank=" + buyer_bank + ", buyer_bankno=" + buyer_bankno + ", buyer_bankname=" + buyer_bankname
				+ ", buyer_zip=" + buyer_zip + ", buyer_add1=" + buyer_add1 + ", buyer_add2=" + buyer_add2
				+ ", buyer_comtel=" + buyer_comtel + ", buyer_fax=" + buyer_fax + ", buyer_mail=" + buyer_mail
				+ ", buyer_charger=" + buyer_charger + ", buyer_telext=" + buyer_telext + "]";
	}
	
	
}
