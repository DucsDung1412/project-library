package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.thongTinCaNhan;
import model.user;
import model.yeuCau;
import util.hibernateUtil;

public class thongTinCaNhanDAO implements daoInterface<thongTinCaNhan>{
	public static thongTinCaNhanDAO getthongTinCaNhanDAO() {
		return new thongTinCaNhanDAO();
	}

	@Override
	public boolean insertX(thongTinCaNhan ttcn) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction t = s.beginTransaction();
					
					s.save(ttcn);
					
					t.commit();
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
	public boolean deletaX(thongTinCaNhan ttcn) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					ttcn.setTrangThai("Đã xóa");
					s.update(ttcn);
					
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
	public boolean updateX(thongTinCaNhan ttcn) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction t = s.beginTransaction();
					
					s.update(ttcn);
					
					t.commit();
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
	public thongTinCaNhan selectG(thongTinCaNhan ttcn) {
		thongTinCaNhan thongTin = new thongTinCaNhan();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					thongTin = s.get(thongTinCaNhan.class, ttcn.getMaTTCN());
					
					ts.commit();
				} finally {
					s.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return thongTin;
	}

	@Override
	public List<thongTinCaNhan> selectAll() {
		List<thongTinCaNhan> list = new ArrayList<thongTinCaNhan>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM thongTinCaNhan ttcn WHERE ttcn.trangThai != 'Đã xóa'";
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
	
	public List<thongTinCaNhan> selectTheoString(String traTheo, String duLieu) {
		List<thongTinCaNhan> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM thongTinCaNhan ttcn WHERE ttcn." + traTheo + " LIKE :id AND ttcn.trangThai != 'Đã xóa'";
					Query query = s.createQuery(hql);
					query.setParameter("id", "%" + duLieu + "%");
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
	
	public List<thongTinCaNhan> selectTheoObj(user duLieu) {
		List<thongTinCaNhan> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM thongTinCaNhan ttcn WHERE ttcn.email.username LIKE :email AND ttcn.trangThai != 'Đã xóa'";
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
