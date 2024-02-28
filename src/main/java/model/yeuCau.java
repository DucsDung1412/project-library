package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class yeuCau {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-generator")
    @GenericGenerator(name = "custom-generator", strategy = "custom.yeuCauGenerator")
	private String maYeuCau;
	private String loaiSach;
	private String sach;
	private String moTa, trangThai;
	
	@ManyToOne
	@JoinColumn(name = "email")
	private user email;

	public yeuCau(String maYeuCau, String loaiSach, String sach, String moTa, user email, String trangThai) {
		this.maYeuCau = maYeuCau;
		this.loaiSach = loaiSach;
		this.sach = sach;
		this.moTa = moTa;
		this.email = email;
		this.trangThai = trangThai;
	}

	public yeuCau() {

	}

	public String getMaYeuCau() {
		return maYeuCau;
	}

	public void setMaYeuCau(String maYeuCau) {
		this.maYeuCau = maYeuCau;
	}

	public String getLoaiSach() {
		return loaiSach;
	}

	public void setLoaiSach(String loaiSach) {
		this.loaiSach = loaiSach;
	}

	public String getSach() {
		return sach;
	}

	public void setSach(String sach) {
		this.sach = sach;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public user getEmail() {
		return email;
	}

	public void setEmail(user email) {
		this.email = email;
	}
	
	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "yeuCau [maYeuCau=" + maYeuCau + ", loaiSach=" + loaiSach + ", sach=" + sach + ", moTa=" + moTa
				+ ", email=" + email + "]";
	}
}
