package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.loaiSach;
import model.phieuMuonSach;
import model.sach;
import util.hibernateUtil;

public class loaiSachDAO implements daoInterface<loaiSach>{
	public static loaiSachDAO getloaiSachDAO() {
		return new loaiSachDAO();
	}

	@Override
	public boolean insertX(loaiSach loaiS) {
	    try {
	        SessionFactory sf = hibernateUtil.getSessionFactory();
	        if (sf != null) {
	            Session s = sf.openSession();
	            Transaction ts = s.beginTransaction();
	            try {
	                
	                s.save(loaiS);
	                
	                ts.commit();
	                return true;
	            } catch (HibernateException e) {
	                e.printStackTrace();
	                ts.rollback(); // Rollback the transaction to avoid any partial changes in the database.
	            } finally {
	                s.close();
	            }
	        } else {
	            System.out.println("SessionFactory is null or not available.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}


	@Override
	public boolean deletaX(loaiSach loaiS) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					loaiSach lsClone = this.selectG(loaiS);
					if(!lsClone.getListSach().isEmpty()) {
						for (sach sach : lsClone.getListSach()) {
							sach.setTrangThai("Đã xóa");
							sachDAO.getsachDAO().deletaX(sach);
						}
					}
					loaiS.setTrangThai("Đã xóa");
					s.update(loaiS);
					
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
	public boolean updateX(loaiSach loaiS) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					s.update(loaiS);
					
					ts.commit();
				} finally {
					s.close();
				}
				return true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public loaiSach selectG(loaiSach loaiS) {
		loaiSach ls = new loaiSach();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
				 	ls = ((s.get(loaiSach.class, loaiS.getMaLoaiSach())));
				 	if(ls != null) {
				 		int i = (ls.getListSach().size());
				 	}
//					ls = s.get(loaiSach.class, loaiS.getMaLoaiSach());
					
					ts.commit();
				} finally {
					s.close();
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ls;
	}

	@Override
	public List<loaiSach> selectAll() {
		List<loaiSach> list = new ArrayList<loaiSach>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM loaiSach ls WHERE ls.trangThai != 'Đã xóa'";
					Query query = s.createQuery(hql);
					list = query.getResultList();
					
					for (loaiSach lsach : list) {
						List<sach> listS_LS = new ArrayList<>();
						List<sach> listS = sachDAO.getsachDAO().selectAll();
						for (sach sach : listS) {
							if(sach.getMaLoaiSach().getMaLoaiSach().equals(lsach.getMaLoaiSach())) {
								listS_LS.add(sach);
							}
						}
						lsach.setListSach(listS_LS);
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

	public List<loaiSach> selectTheoString(String traTheo, String duLieu) {
		List<loaiSach> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM loaiSach ls WHERE ls." + traTheo + " LIKE :id AND ls.trangThai != 'Đã xóa'";
					Query query = s.createQuery(hql);
					query.setParameter("id", duLieu);
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
	
	public loaiSach selectTheoTen(String traTheo, String duLieu) {
		loaiSach ls = new loaiSach();
		List<loaiSach> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM loaiSach ls WHERE ls." + traTheo + " LIKE :id AND ls.trangThai != 'Đã xóa'";
					Query query = s.createQuery(hql);
					query.setParameter("id", duLieu);
					list = query.getResultList();
					ls = list.get(0);
					
					ts.commit();
				} finally {
					s.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ls;
	}
	
	public loaiSach selectTheoTenLS(String string) {
		loaiSach ls = new loaiSach();
		List<loaiSach> list = new ArrayList<loaiSach>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM loaiSach ls WHERE ls.tenLoaiSach = :string";
					Query query = s.createQuery(hql);
					query.setParameter("string", string);
					list = query.getResultList();
					
					ls = list.get(0);
					
					List<sach> listS_LS = new ArrayList<>();
					for (loaiSach lsach : list) {
						List<sach> listS = sachDAO.getsachDAO().selectAll();
						for (sach sach : listS) {
							if(sach.getMaLoaiSach().getMaLoaiSach().equals(lsach.getMaLoaiSach())) {
								listS_LS.add(sach);
							}
						}
						lsach.setListSach(listS_LS);
					}
					
					ts.commit();
				} finally {
					s.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ls;
	}
}
