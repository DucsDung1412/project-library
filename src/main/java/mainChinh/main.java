//package mainChinh;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//
//import dao.danhGiaDAO;
//import dao.loaiSachDAO;
//import dao.phieuMuonSachDAO;
//import dao.sachDAO;
//import dao.thongTinCaNhanDAO;
//import dao.userDAO;
//import dao.yeuCauDAO;
//import model.danhGia;
//import model.loaiSach;
//import model.phieuMuonSach;
//import model.sach;
//import model.thongTinCaNhan;
//import model.user;
//import model.yeuCau;
//import util.hibernateUtil;
//
//public class main {
//	public static void main(String[] args) {
////		List<Object[]> lsit = phieuMuonSachDAO.getphieuMuonSachDAO().selectDoPhoBien();
//		
////		for (Object[] objects : lsit) {
////			System.out.println(objects[0]);
////			System.out.println(((loaiSach)objects[1]).getMaLoaiSach());
////		}
//		
////		List<sach> listSach = sachDAO.getsachDAO().selectAll();
////		for (sach sach : listSach) {
////			for (phieuMuonSach pms : sach.getListPM()) {
////				for(danhGia dg : pms.getListDG()) {
////					System.out.println(pms.getMaPhieu() + dg.getMaDanhGia());
////				}
////			}
////			System.out.println(sach.getMaSach());
////		}
//		
////		List<user> listSach = userDAO.getuserDAO().selectAll();
////		for (user loaiSach : listSach) {
////			System.out.println(loaiSach.getUsername());
////			for (danhGia dg : loaiSach.getListDG()) {
////				System.out.println(dg.getEmail().getUsername() + ": " + dg.getMaDanhGia());
////			}
////			System.out.println(loaiSach.getListPM().size());
////			System.out.println(loaiSach.getListYC().size());
////		}
//		
//		
//		user u = new user("admin", "asdh", "xc");
////		Boolean bb = (userDAO.getuserDAO().insertX(u)) ;
////		System.out.println(bb);
////		if(!bb) {
////			System.out.println("\nEmail da ton tai\n");
////		}
//		
//		
//		thongTinCaNhan ttcn = new thongTinCaNhan("TTCN2", u, "admin", "0000000000", "Khong biet", "ajsd");
////		thongTinCaNhanDAO.getthongTinCaNhanDAO().insertX(ttcn);
//		
//		loaiSach ls = new loaiSach("LS01", "vljaksd", "jkjk");
////		Boolean b = loaiSachDAO.getloaiSachDAO().insertX(ls);
////		if(!b) {
////			loaiSachDAO.getloaiSachDAO().updateX(ls);
////		}
//		
//		sach s = new sach("MS99", "Sach test", "Dung", "asnd.png", "asdjbhhj", "VipPro", 2021, 21, 1, ls);
////		sachDAO.getsachDAO().insertX(s);
////		sachDAO.getsachDAO().deletaX(s);
//		
//		phieuMuonSach pms = new phieuMuonSach("PM1", null, null, u, s, "Dang dat");
////		phieuMuonSachDAO.getphieuMuonSachDAO().insertX(pms);
//		
//		danhGia dg = new danhGia("DG1", "Ngu", u, pms);
////		danhGiaDAO.getdanhGiaDAO().insertX(dg);
//		
//		yeuCau yc = new yeuCau("YC1", "asdh", "ghjg", "aksdhjkzxv", u);
////		yeuCauDAO.getyeuCauDAO().insertX(yc);
//		
////		yeuCau y = yeuCauDAO.getyeuCauDAO().selectG(new yeuCau("YC2", "asdh", "ghjg", "aksdhjkzxv", u));
////		if(y == null) {
////			System.out.println("\nKhong kiem thay yeu cau nay");
////		}
//		
////		danhGiaDAO.getdanhGiaDAO().deletaX(dg);
////		loaiSachDAO.getloaiSachDAO().deletaX(ls);
//		
////		List<danhGia> list = danhGiaDAO.getdanhGiaDAO().selectAll();
////		
////		for (danhGia danhGia : list) {
////			System.out.println(danhGia);
////		}
//		
////		List<phieuMuonSach> list = phieuMuonSachDAO.getphieuMuonSachDAO().selectAll();
//		
////		for (phieuMuonSach danhGia : list) {
////			System.out.println(danhGia.getListDG().size() + " " + danhGia.getEmail().getUsername());
////		}
//		
////		List<sach> list = sachDAO.getsachDAO().selectAll();
////		
////		for (sach sach : list) {
////			for (phieuMuonSach pm : sach.getListPM()) {
////				System.out.println(pm.getListDG().get(0));
////			}
////		}
//		
////		List<loaiSach> list = loaiSachDAO.getloaiSachDAO().selectAll();
////		
////		
////		for (loaiSach loaiSach : list) {
////			for (sach sach : loaiSach.getListSach()) {
////				System.out.println(sach.getTenSach() + sach.getListPM().size());
////			}
////		}
////		
////		System.out.println(yeuCauDAO.getyeuCauDAO().selectG(yc).getEmail().getUsername());
//		
////		List<yeuCau> list = yeuCauDAO.getyeuCauDAO().selectAll();
////		
////		for (yeuCau yeuCau : list) {
////			System.out.println(yeuCau);
////		}
//		
////		System.out.println(thongTinCaNhanDAO.getthongTinCaNhanDAO().selectAll().get(0));
//		
////		System.out.println(userDAO.getuserDAO().selectG(u).getTtcn());
//		
////		List<loaiSach> list = loaiSachDAO.getloaiSachDAO().selectAll();
//		
////		List<user> list = userDAO.getuserDAO().selectAll();
//		
////		user u1 = userDAO.getuserDAO().selectG(u);
////		for (yeuCau yeuC : u1.getListYC()) {
////			System.out.println(yeuC.getMaYeuCau());
////		}
//		
////		for (user user : list) {
////			System.out.println(user.getUsername());
////			for (yeuCau yeuC : user.getListYC()) {
////				System.out.println(yeuC.getMaYeuCau());
////			}
////			System.out.println(user.getListDG().size());
////			System.out.println(user.getListPM().size());
////			System.out.println(user.getListYC().size());
////			System.out.println(user.getTtcn().getDiaChi());
////		}
//		
////		for (loaiSach danhGia : list) {
////			System.out.println(danhGia.getListSach().size());
////		}
//		
////		System.out.println(loaiSachDAO.getloaiSachDAO().selectG(ls).getListSach().size());
//		
////		System.out.println(phieuMuonSachDAO.getphieuMuonSachDAO().selectG(pms).getListDG().size());
//		
//		
////		yeuCauDAO.getyeuCauDAO().deletaX(yc);
//		
////		danhGiaDAO.getdanhGiaDAO().deletaX(dg);
//		
////		phieuMuonSachDAO.getphieuMuonSachDAO().deletaX(pms);
//		
////		sachDAO.getsachDAO().deletaX(s);
//		
////		loaiSachDAO.getloaiSachDAO().deletaX(ls);
//		
////		userDAO.getuserDAO().deletaX(u);
//		
////		SessionFactory sf = hibernateUtil.getSessionFactory();
////		if(sf != null) {
////			Session ses = sf.openSession();
////			try {
////				Transaction ts = ses.beginTransaction();
////				
////			 	loaiSach LSS = ((ses.get(loaiSach.class, ls.getMaLoaiSach())));
////			 	System.out.println(LSS.getListSach().size());
////				
////				ts.commit();
////			} finally {
////				ses.close();
////			}
////		}
//		
//	}
//}
