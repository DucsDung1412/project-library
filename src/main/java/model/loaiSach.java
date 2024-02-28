package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class loaiSach {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-generator")
    @GenericGenerator(name = "custom-generator", strategy = "custom.loaiSachGenerator")
	private String maLoaiSach;
	private String tenLoaiSach, moTa, trangThai;
	
	@OneToMany(mappedBy = "maLoaiSach", cascade = CascadeType.ALL)
	private List<sach> listSach;

	public loaiSach(String maLoaiSach, String tenLoaiSach, String moTa, String trangThai) {
		this.maLoaiSach = maLoaiSach;
		this.tenLoaiSach = tenLoaiSach;
		this.moTa = moTa;
		this.trangThai = trangThai;
	}
	
	public loaiSach() {

	}

	public String getMaLoaiSach() {
		return maLoaiSach;
	}

	public void setMaLoaiSach(String maLoaiSach) {
		this.maLoaiSach = maLoaiSach;
	}

	public String getTenLoaiSach() {
		return tenLoaiSach;
	}

	public void setTenLoaiSach(String tenLoaiSach) {
		this.tenLoaiSach = tenLoaiSach;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public List<sach> getListSach() {
		return listSach;
	}

	public void setListSach(List<sach> listSach) {
		this.listSach = listSach;
	}
	
	public String getSach() {
		String s = "";
		for (sach sach : listSach) {
			s += sach.getMaSach();
			s += " ";
		}
		return s;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
//	@Override
//	public String toString() {
//		return "loaiSach [maLoaiSach=" + maLoaiSach + ", tenLoaiSach=" + tenLoaiSach + ", moTa=" + moTa + ", listSach="
//				+ listSach.size() + "]";
//	}

	
}
