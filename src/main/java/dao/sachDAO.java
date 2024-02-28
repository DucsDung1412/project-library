package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.loaiSach;
import model.phieuMuonSach;
import model.sach;
import util.hibernateUtil;

public class sachDAO implements daoInterface<sach>{
	public static sachDAO getsachDAO() {
		return new sachDAO();
	}

	@Override
	public boolean insertX(sach sachS) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					s.save(sachS);
					
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
	public boolean deletaX(sach sachS) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					sach sachClone = this.selectG(sachS);
					if(!sachClone.getListPM().isEmpty()) {
						for (phieuMuonSach pms : sachClone.getListPM()) {
							pms.setTrangThai("Đã xóa");
							phieuMuonSachDAO.getphieuMuonSachDAO().deletaX(pms);
						}
					}
					
					sachS.setTrangThai("Đã xóa");
					s.update(sachS);
					
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
	public boolean updateX(sach sachS) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					s.update(sachS);
					
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
	public sach selectG(sach sachS) {
		sach sa = new sach();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					sa = s.get(sach.class, sachS.getMaSach());
					if(sa != null) {
						sa.getListPM().size();
					}
					
					ts.commit();
				} finally {
					s.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sa;
	}

	@Override
	public List<sach> selectAll() {
		List<sach> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM sach s WHERE s.trangThai != 'Đã xóa'";
					Query query = s.createQuery(hql);
					list = query.getResultList();
					
					for (sach sach : list) {
						List<phieuMuonSach> listPM_sach = new ArrayList<>();
						List<phieuMuonSach> listPM = phieuMuonSachDAO.getphieuMuonSachDAO().selectAll();
						for (phieuMuonSach pms : listPM) {
							if(pms.getMaSach().getMaSach().equals(sach.getMaSach())) {
								listPM_sach.add(pms);
							}
						}
						sach.setListPM(listPM_sach);
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

	public List<sach> selectTheoString(String traTheo, String duLieu) {
		List<sach> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM sach s WHERE s." + traTheo + " LIKE :id AND s.trangThai != 'Đã xóa'";
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

	public Long soLuongSach() {
		long result = 0;
		List<Long> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
						
					String hql = "SELECT COUNT(s) FROM sach s WHERE s.trangThai != 'Đã xóa'";
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
	
	public sach selectTheoTenSach(String string) {
		sach sa = new sach();
		List<sach> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM sach s WHERE s.tenSach = :string AND s.trangThai != 'Đã xóa'";
					Query query = s.createQuery(hql);
					query.setParameter("string", string);
					list = query.getResultList();
					
					sa = list.get(0);
					
					if(sa!=null) {
						sa.getListPM().size();
					}
					
					ts.commit();
				} finally {
					s.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sa;
	}
	
	public List<sach> selectTheoLS(loaiSach ls) {
		List<sach> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM sach s WHERE s.maLoaiSach.maLoaiSach = :maLoaiSach";
					Query query = s.createQuery(hql);
					query.setParameter("maLoaiSach", ls.getMaLoaiSach());
					list = query.getResultList();
					
					List<phieuMuonSach> listPM_sach = new ArrayList<>();
					for (sach sach : list) {
						List<phieuMuonSach> listPM = phieuMuonSachDAO.getphieuMuonSachDAO().selectAll();
						for (phieuMuonSach pms : listPM) {
							if(pms.getMaSach().getMaSach().equals(sach.getMaSach())) {
								listPM_sach.add(pms);
							}
						}
						sach.setListPM(listPM_sach);
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
}
