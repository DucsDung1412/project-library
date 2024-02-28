package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.danhGia;
import model.phieuMuonSach;
import model.thongTinCaNhan;
import model.user;
import model.yeuCau;
import util.hibernateUtil;

public class userDAO implements daoInterface<user>{
	public static userDAO getuserDAO() {
		return new userDAO();
	}
	
	@Override
	public boolean insertX(user us) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					s.save(us);
					
					ts.commit();
					
				} finally {
					s.close();
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deletaX(user us) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					user userClone = this.selectG(us);
					if(!userClone.getListDG().isEmpty()) {
						for (danhGia dg : userClone.getListDG()) {
							danhGiaDAO.getdanhGiaDAO().deletaX(dg);
						}
					}
					if(!userClone.getListPM().isEmpty()) {
						for (phieuMuonSach pms : userClone.getListPM()) {
							phieuMuonSachDAO.getphieuMuonSachDAO().deletaX(pms);
						}
					}
					if(!userClone.getListYC().isEmpty()) {
						for (yeuCau yc : userClone.getListYC()) {
							yeuCauDAO.getyeuCauDAO().deletaX(yc);
						}
					}
					if(userClone.getTtcn() != null) {
						thongTinCaNhanDAO.getthongTinCaNhanDAO().deletaX(userClone.getTtcn());
					}			
					
					us.setTrangThai("Đã xóa");
					s.update(us);
					ts.commit();
				} finally {
					s.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateX(user us) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					s.update(us);
					
					ts.commit();
					
				} finally {
					s.close();
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public user selectG(user us) {
		user u = new user();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					u = s.get(user.class, us.getUsername());
					if(u != null) {
						u.getListDG().size();
						u.getListPM().size();
						u.getListYC().size();
						u.getTtcn();
					}
					
					ts.commit();
				} finally {
					s.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public List<user> selectAll() {
		List<user> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM user us WHERE us.trangThai != 'Đã xóa'";
					Query query = s.createQuery(hql);
					list = query.getResultList();
					
					for (user us : list) {
						thongTinCaNhan ttcn_user = new thongTinCaNhan();
						List<phieuMuonSach> listPMS_user = new ArrayList<>();
						List<yeuCau> listYC_user = new ArrayList<>();
						
						List<danhGia> listDG_user = new ArrayList<>();
						List<danhGia> lsiDG = danhGiaDAO.getdanhGiaDAO().selectAll();
						for (danhGia danhGia : lsiDG) {
							if(danhGia.getEmail().getUsername().equals(us.getUsername())) {
								System.out.println("\n"+danhGia.getMaDanhGia()+"\n");
								listDG_user.add(danhGia);
							}
						}
						us.setListDG(listDG_user);
						
						List<phieuMuonSach> lsiPM = phieuMuonSachDAO.getphieuMuonSachDAO().selectAll();
						for (phieuMuonSach phieuMuonSach : lsiPM) {
							if(phieuMuonSach.getEmail().getUsername().equals(us.getUsername())) {
								listPMS_user.add(phieuMuonSach);
							}
						}
						us.setListPM(listPMS_user);
						
						List<yeuCau> listYC = yeuCauDAO.getyeuCauDAO().selectAll();
						for (yeuCau yeuCau : listYC) {
							if(yeuCau.getEmail().getUsername().equals(us.getUsername())) {
								listYC_user.add(yeuCau);
							}
						}
						us.setListYC(listYC_user);
						
						List<thongTinCaNhan> listTTCN = thongTinCaNhanDAO.getthongTinCaNhanDAO().selectAll();
						for (thongTinCaNhan thongTinCaNhan : listTTCN) {
							if(thongTinCaNhan.getEmail().getUsername().equals(us.getUsername())) {
								ttcn_user = thongTinCaNhan;
								break;
							}
						}
						us.setTtcn(ttcn_user);
					}
					
					ts.commit();
				} finally {
					s.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public Long soNguoiDung() {
		long result = 0;
		List<Long> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
						
					String hql = "SELECT COUNT(u) FROM user u WHERE u.trangThai != 'Đã xóa'";
					Query query = s.createQuery(hql);
					list = query.getResultList();
					
					
					result = list.get(0);
					

					ts.commit();
				} finally {
					s.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return result;
	}
}
