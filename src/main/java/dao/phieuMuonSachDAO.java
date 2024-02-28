package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.danhGia;
import model.phieuMuonSach;
import model.sach;
import model.thongTinCaNhan;
import model.user;
import util.hibernateUtil;

public class phieuMuonSachDAO implements daoInterface<phieuMuonSach>{
	public static phieuMuonSachDAO getphieuMuonSachDAO() {
		return new phieuMuonSachDAO();
	}

	@Override
	public boolean insertX(phieuMuonSach pms) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					s.save(pms);
					
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
	public boolean deletaX(phieuMuonSach pms) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					phieuMuonSach pmsClone = this.selectG(pms);
					if(!pmsClone.getListDG().isEmpty()) {
						for (danhGia dg : pmsClone.getListDG()) {
							dg.setTrangThai("Đã xóa");
							danhGiaDAO.getdanhGiaDAO().deletaX(dg);
						}
					}
					pms.setTrangThai("Đã xóa");
					s.update(pms);
					
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
	public boolean updateX(phieuMuonSach pms) {
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					s.update(pms);
					
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
	public phieuMuonSach selectG(phieuMuonSach pms) {
		phieuMuonSach pm = new phieuMuonSach();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					pm = s.get(phieuMuonSach.class, pms.getMaPhieu());
					pm.getListDG().size();
					
					
					ts.commit();
				} finally {
					s.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pm;
	}

	@Override
	public List<phieuMuonSach> selectAll() {
		List<phieuMuonSach> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM phieuMuonSach pms WHERE pms.trangThai != 'Đã xóa'";
					Query query = s.createQuery(hql);
					list = query.getResultList();
					
					for (phieuMuonSach phieuMuonSach : list) {
						List<danhGia> listDG_PMS = new ArrayList<>();
						List<danhGia> lsiDG = danhGiaDAO.getdanhGiaDAO().selectAll();
						for (danhGia danhGia : lsiDG) {
							if(danhGia.getMaPhieuMuon().getMaPhieu().equals(phieuMuonSach.getMaPhieu())) {
								listDG_PMS.add(danhGia);
							}
						}
						phieuMuonSach.setListDG(listDG_PMS);
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
	
	public List<Object[]> selectDoPhoBien() {
		List<Object[]> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "SELECT COUNT(pms.maSach), pms.maSach, pms.maPhieu FROM phieuMuonSach pms WHERE pms.trangThai != 'Đã xóa' GROUP BY pms.maSach";
					Query query = s.createQuery(hql, Object[].class);
					query.setMaxResults(10);
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

	public Long soLuongSachDaQuaHan() {
		long result = 0;
		List<Long> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
						
					String hql = "SELECT COUNT(pms) FROM phieuMuonSach pms WHERE pms.trangThaiPhieu like 'Đã quá hạn' AND pms.trangThai != 'Đã xóa'";
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

	public Long soLuongSachDangDat() {
		long result = 0;
		List<Long> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
						
					String hql = "SELECT COUNT(pms) FROM phieuMuonSach pms WHERE pms.trangThaiPhieu like 'Đang đặt' AND pms.trangThai != 'Đã xóa'";
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
	
	public Long soLuongSachDangMuon() {
		long result = 0;
		List<Long> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
						
					String hql = "SELECT COUNT(pms) FROM phieuMuonSach pms WHERE pms.trangThaiPhieu like 'Đang mượn' AND pms.trangThai != 'Đã xóa'";
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
	
	public List<phieuMuonSach> selectAllQuaHan() {
        List<phieuMuonSach> list = new ArrayList<>();

        try {
            SessionFactory sf = hibernateUtil.getSessionFactory();
            if(sf != null) {
                Session s = sf.openSession();
                try {
                    Transaction ts = s.beginTransaction();

                    String hql = "SELECT pms FROM phieuMuonSach pms WHERE pms.trangThaiPhieu like 'Đã quá hạn' AND pms.trangThai != 'Đã xóa' ORDER BY pms.ngayMuon ASC";
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
	
	public List<phieuMuonSach> selectAllDangMuon() {
        List<phieuMuonSach> list = new ArrayList<>();

        try {
            SessionFactory sf = hibernateUtil.getSessionFactory();
            if(sf != null) {
                Session s = sf.openSession();
                try {
                    Transaction ts = s.beginTransaction();

                    String hql = "SELECT pms FROM phieuMuonSach pms WHERE pms.trangThaiPhieu like 'Đang mượn' AND pms.trangThai != 'Đã xóa' ORDER BY pms.ngayMuon ASC";
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
	
	public List<phieuMuonSach> selectAllDangDat() {
        List<phieuMuonSach> list = new ArrayList<>();

        try {
            SessionFactory sf = hibernateUtil.getSessionFactory();
            if(sf != null) {
                Session s = sf.openSession();
                try {
                    Transaction ts = s.beginTransaction();

                    String hql = "SELECT pms FROM phieuMuonSach pms WHERE pms.trangThaiPhieu like 'Đang đặt' AND pms.trangThai != 'Đã xóa' ORDER BY pms.ngayMuon ASC";
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
	
	public List<phieuMuonSach> selectAllDaTra() {
        List<phieuMuonSach> list = new ArrayList<>();

        try {
            SessionFactory sf = hibernateUtil.getSessionFactory();
            if(sf != null) {
                Session s = sf.openSession();
                try {
                    Transaction ts = s.beginTransaction();

                    String hql = "SELECT pms FROM phieuMuonSach pms WHERE pms.trangThaiPhieu like 'Đã trả' AND pms.trangThai != 'Đã xóa' ORDER BY pms.ngayMuon ASC";
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
	
	public phieuMuonSach selectDangDat(sach sa, user u) {
	    phieuMuonSach pm = null; // Initialize to null to handle no matching record case

	    if (sa != null && u != null) { // Check for null arguments
	        try (Session s = hibernateUtil.getSessionFactory().openSession()) {
	            Transaction ts = s.beginTransaction();

	            String hql = "FROM phieuMuonSach pms WHERE pms.email = :email AND pms.maSach = :maSach AND pms.trangThaiPhieu = 'Đang đặt' AND pms.trangThai != 'Đã xóa'";
	            Query query = s.createQuery(hql);
	            query.setParameter("email", u);
	            query.setParameter("maSach", sa);

	            List<phieuMuonSach> list = query.getResultList();
	            if (!list.isEmpty()) {
	                pm = list.get(0);
	            }

	            ts.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return pm;
	}
	
	public phieuMuonSach selectDangMuon(sach sa, user u) {
	    phieuMuonSach pm = null; // Initialize to null to handle no matching record case

	    if (sa != null && u != null) { // Check for null arguments
	        try (Session s = hibernateUtil.getSessionFactory().openSession()) {
	            Transaction ts = s.beginTransaction();

	            String hql = "FROM phieuMuonSach pms WHERE pms.email = :email AND pms.maSach = :maSach AND pms.trangThaiPhieu = 'Đang mượn' AND pms.trangThai != 'Đã xóa'";
	            Query query = s.createQuery(hql);
	            query.setParameter("email", u);
	            query.setParameter("maSach", sa);

	            List<phieuMuonSach> list = query.getResultList();
	            if (!list.isEmpty()) {
	                pm = list.get(0);
	            }

	            ts.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return pm;
	}
	
	public phieuMuonSach selectQuaHan(sach sa, user u) {
	    phieuMuonSach pm = null; // Initialize to null to handle no matching record case

	    if (sa != null && u != null) { // Check for null arguments
	        try (Session s = hibernateUtil.getSessionFactory().openSession()) {
	            Transaction ts = s.beginTransaction();

	            String hql = "FROM phieuMuonSach pms WHERE pms.email = :email AND pms.maSach = :maSach AND pms.trangThaiPhieu = 'Đã quá hạn' AND pms.trangThai != 'Đã xóa'";
	            Query query = s.createQuery(hql);
	            query.setParameter("email", u);
	            query.setParameter("maSach", sa);

	            List<phieuMuonSach> list = query.getResultList();
	            if (!list.isEmpty()) {
	                pm = list.get(0);
	            }

	            ts.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return pm;
	}
	
	public List<phieuMuonSach> selectTheoString(String traTheo, String duLieu, String option) {
		List<phieuMuonSach> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM phieuMuonSach pms WHERE pms." + traTheo + " LIKE :id AND pms.trangThai != 'Đã xóa' AND pms.trangThaiPhieu = :option ORDER BY pms.ngayMuon ASC";
					Query query = s.createQuery(hql);
					query.setParameter("id", "%" + duLieu + "%");
					query.setParameter("option", option);
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
	
	public List<phieuMuonSach> selectTheoObj(user duLieu, String option) {
		List<phieuMuonSach> list = new ArrayList<>();
		
		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			if(sf != null) {
				Session s = sf.openSession();
				try {
					Transaction ts = s.beginTransaction();
					
					String hql = "FROM phieuMuonSach pms WHERE pms.email.username LIKE :email AND pms.trangThai != 'Đã xóa' AND pms.trangThaiPhieu = :option ORDER BY pms.ngayMuon ASC";
					Query query = s.createQuery(hql);
					query.setParameter("email", "%" + duLieu.getUsername() + "%");
					query.setParameter("option", option);
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
