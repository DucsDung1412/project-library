package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.danhGia;
import model.thongTinCaNhan;
import model.user;
import model.yeuCau;
import util.hibernateUtil;

public class yeuCauDAO implements daoInterface<yeuCau>{
	public static yeuCauDAO getyeuCauDAO() {
		return new yeuCauDAO();
	}

	@Override
	public boolean insertX(yeuCau yeuC) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			
			if(sf != null) {
				Session s =sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					s.save(yeuC);
					
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
	public boolean deletaX(yeuCau yeuC) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					yeuC.setTrangThai("Đã xóa");
					s.update(yeuC);
					
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
	public boolean updateX(yeuCau yeuC) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			
			if(sf != null) {
				Session s =sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					s.update(yeuC);
					
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
	public yeuCau selectG(yeuCau yeuC) {
		yeuCau yc = new yeuCau();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					yc = s.get(yeuCau.class, yeuC.getMaYeuCau());
					
					ts.commit();
				} finally {
					s.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return yc;
	}

	@Override
	public List<yeuCau> selectAll() {
		List<yeuCau> list = new ArrayList<yeuCau>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM yeuCau yc WHERE yc.trangThai != 'Đã xóa'";
					Query query = s.createQuery(hql);
					list = query.getResultList();
					
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
	
	public List<yeuCau> selectTheoObj(user duLieu) {
		List<yeuCau> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM yeuCau yc WHERE yc.email.username LIKE :email AND yc.trangThai != 'Đã xóa'";
					Query query = s.createQuery(hql);
					query.setParameter("email", "%" + duLieu.getUsername() + "%");
					System.out.println(duLieu.getUsername());
					list = query.getResultList();
					
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
}
