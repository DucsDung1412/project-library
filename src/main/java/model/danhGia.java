package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class danhGia {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-generator")
    @GenericGenerator(name = "custom-generator", strategy = "custom.danhGiaGenerator")
	private String maDanhGia;
	private String danhGia, trangThai;
	
	@ManyToOne
	@JoinColumn(name = "email")
	private user email;
	
	@ManyToOne
	@JoinColumn(name = "maPhieuMuon")
	private phieuMuonSach maPhieuMuon;

	public danhGia(String maDanhGia, String danhGia, user email, phieuMuonSach maPhieuMuon, String trangThai) {
		this.maDanhGia = maDanhGia;
		this.danhGia = danhGia;
		this.email = email;
		this.maPhieuMuon = maPhieuMuon;
		this.trangThai = trangThai;
	}

	public danhGia() {

	}

	public String getMaDanhGia() {
		return maDanhGia;
	}

	public void setMaDanhGia(String maDanhGia) {
		this.maDanhGia = maDanhGia;
	}

	public String getDanhGia() {
		return danhGia;
	}

	public void setDanhGia(String danhGia) {
		this.danhGia = danhGia;
	}

	public user getEmail() {
		return email;
	}

	public void setEmail(user email) {
		this.email = email;
	}

	public phieuMuonSach getMaPhieuMuon() {
		return maPhieuMuon;
	}

	public void setMaPhieuMuon(phieuMuonSach maPhieuMuon) {
		this.maPhieuMuon = maPhieuMuon;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "danhGia [maDanhGia=" + maDanhGia + ", danhGia=" + danhGia + ", email=" + email + ", maPhieuMuon="
				+ maPhieuMuon + "]";
	}
}
