-- --------------------------------------------------------
-- Host:                         
-- Server version:               10.4.27-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for library_system
CREATE DATABASE IF NOT EXISTS `library_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `library_system`;

-- Dumping structure for table library_system.danhgia
CREATE TABLE IF NOT EXISTS `danhgia` (
  `maDanhGia` varchar(255) NOT NULL,
  `danhGia` varchar(255) DEFAULT NULL,
  `trangThai` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `maPhieuMuon` varchar(255) NOT NULL,
  PRIMARY KEY (`maDanhGia`),
  KEY `FKqhrjsyuacoxcnytt9lnthk48v` (`email`),
  KEY `FKelxt8i4myxyku7mpxdpxne69v` (`maPhieuMuon`),
  CONSTRAINT `FKelxt8i4myxyku7mpxdpxne69v` FOREIGN KEY (`maPhieuMuon`) REFERENCES `phieumuonsach` (`maPhieu`),
  CONSTRAINT `FKqhrjsyuacoxcnytt9lnthk48v` FOREIGN KEY (`email`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table library_system.danhgia: ~3 rows (approximately)
REPLACE INTO `danhgia` (`maDanhGia`, `danhGia`, `trangThai`, `email`, `maPhieuMuon`) VALUES
	('DG Fri Aug 04 11:44:28 ICT 2023', 'Sách hay, mọi người nên mượn về đọc', 'Tồn tại', 'daoducdung2000@gmail.com', 'PMS Fri Aug 04 10:44:28 ICT 2023'),
	('DG Fri Aug 05 11:44:28 ICT 2023', 'Sách rất bổ ích cho kiến thữc của chúng ta', 'Tồn tại', 'nguyenhoahung1007@gmail.com', 'PMS Fri Aug 07 10:44:28 ICT 2023'),
	('DG Fri Aug 06 11:44:28 ICT 2023', 'Sách không hay mọi người không nên đọc', 'Tồn tại', 'nguyenleloi2k3@gmail.com', 'PMS Fri Aug 06 10:44:28 ICT 2023');

-- Dumping structure for table library_system.loaisach
CREATE TABLE IF NOT EXISTS `loaisach` (
  `maLoaiSach` varchar(255) NOT NULL,
  `moTa` varchar(255) DEFAULT NULL,
  `tenLoaiSach` varchar(255) NOT NULL,
  `trangThai` varchar(255) NOT NULL,
  PRIMARY KEY (`maLoaiSach`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table library_system.loaisach: ~5 rows (approximately)
REPLACE INTO `loaisach` (`maLoaiSach`, `moTa`, `tenLoaiSach`, `trangThai`) VALUES
	('LS Tue Aug 01 18:02:54 ICT 2023', 'Sách dành cho thiết kế đồ họa', 'Thiết kế đồ hoạ', 'Tồn tại'),
	('LS Tue Aug 02 18:02:54 ICT 2023', 'Sách sử dụng chung trong ngành công nghệ thông tin', 'Công nghệ thông tin', 'Tồn tại'),
	('LS Tue Aug 03 18:02:54 ICT 2023', 'Sách chuyên về tin học văn phòng', 'Tin học văn phòng', 'Tồn tại'),
	('LS Tue Aug 04 18:02:54 ICT 2023', 'Sách marketing', 'Marketing', 'Đã xoá'),
	('LS Tue Aug 08 18:26:36 ICT 2023', 'asd', 'akshd - asjhd', 'Đã xóa');

-- Dumping structure for table library_system.phieumuonsach
CREATE TABLE IF NOT EXISTS `phieumuonsach` (
  `maPhieu` varchar(255) NOT NULL,
  `ngayMuon` date NOT NULL,
  `ngayTra` date NOT NULL,
  `trangThai` varchar(255) NOT NULL,
  `trangThaiPhieu` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `maSach` varchar(255) NOT NULL,
  PRIMARY KEY (`maPhieu`),
  KEY `FKb10dgvyx6u9pfrsnsrecswgyg` (`email`),
  KEY `FKb0gsg5mpbd9i7hdr3xgmb7f2g` (`maSach`),
  CONSTRAINT `FKb0gsg5mpbd9i7hdr3xgmb7f2g` FOREIGN KEY (`maSach`) REFERENCES `sach` (`maSach`),
  CONSTRAINT `FKb10dgvyx6u9pfrsnsrecswgyg` FOREIGN KEY (`email`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table library_system.phieumuonsach: ~8 rows (approximately)
REPLACE INTO `phieumuonsach` (`maPhieu`, `ngayMuon`, `ngayTra`, `trangThai`, `trangThaiPhieu`, `email`, `maSach`) VALUES
	('PMS Fri Aug 04 10:44:28 ICT 2023', '2023-08-02', '2023-09-02', 'Tồn tại', 'Đã trả', 'daoducdung2000@gmail.com', 'S Sat Aug 12 10:10:57 ICT 2023'),
	('PMS Fri Aug 05 10:44:28 ICT 2023', '2023-08-02', '2023-08-03', 'Tồn tại', 'Đã quá hạn', 'nguyenhoahung1007@gmail.com', 'S Sat Aug 06 10:10:57 ICT 2023'),
	('PMS Fri Aug 06 10:44:28 ICT 2023', '2023-08-02', '2023-08-09', 'Tồn tại', 'Đã trả', 'nguyenleloi2k3@gmail.com', 'S Sat Aug 12 10:10:57 ICT 2023'),
	('PMS Fri Aug 07 10:44:28 ICT 2023', '2023-08-03', '2023-08-07', 'Tồn tại', 'Đang mượn', 'nguyenhoahung1007@gmail.com', 'S Sat Aug 12 10:10:57 ICT 2023'),
	('PMS Fri Aug 11 15:56:04 ICT 2023', '2023-08-12', '2023-09-12', 'Tồn tại', 'Đang đặt', 'admin', 'S Sat Aug 05 10:10:57 ICT 2023'),
	('PMS Sat Aug 05 17:58:37 ICT 2023', '2023-08-05', '2023-08-05', 'Tồn tại', 'Đang mượn', 'admin', 'S Sat Aug 07 10:10:57 ICT 2023'),
	('PMS Sat Aug 05 17:59:42 ICT 2023', '2023-08-05', '2023-08-05', 'Tồn tại', 'Đang đặt', 'admin', 'S Sat Aug 12 10:10:57 ICT 2023'),
	('PMS Sat Aug 05 18:00:15 ICT 2023', '2023-08-05', '2023-08-05', 'Tồn tại', 'Đã trả', 'admin', 'S Sat Aug 12 10:10:57 ICT 2023');

-- Dumping structure for table library_system.sach
CREATE TABLE IF NOT EXISTS `sach` (
  `maSach` varchar(255) NOT NULL,
  `hinhSach` varchar(255) NOT NULL,
  `moTa` varchar(9999) NOT NULL DEFAULT '',
  `namXB` int(11) NOT NULL,
  `nhaXuatBan` varchar(255) NOT NULL,
  `soLanTaiBan` int(11) NOT NULL,
  `soLuong` int(11) NOT NULL,
  `tacGia` varchar(255) NOT NULL,
  `tenSach` varchar(255) NOT NULL,
  `trangThai` varchar(255) NOT NULL,
  `maLoaiSach` varchar(255) NOT NULL,
  PRIMARY KEY (`maSach`),
  KEY `FKi1i4w49gqrid7yfay4dtwj01n` (`maLoaiSach`),
  CONSTRAINT `FKi1i4w49gqrid7yfay4dtwj01n` FOREIGN KEY (`maLoaiSach`) REFERENCES `loaisach` (`maLoaiSach`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table library_system.sach: ~9 rows (approximately)
REPLACE INTO `sach` (`maSach`, `hinhSach`, `moTa`, `namXB`, `nhaXuatBan`, `soLanTaiBan`, `soLuong`, `tacGia`, `tenSach`, `trangThai`, `maLoaiSach`) VALUES
	('S Sat Aug 05 10:10:57 ICT 2023', 'MS01.png', 'Sách “THIẾT KẾ KIẾN TRÚC - RENDER VỚI SKETCHUP VÀ REVIT” giới thiệu tới bạn đọc sử dụng Lumion một chương trình đầy quyền năng trong Render Realtime qua những công cụ và lệnh cơ bản nhất của chương trình, giúp người học có thể render các thiết kế 3D từ cá', 2021, 'Thanh Niên', 1, 20, 'Phạm Quang Hiển', 'Thiết Kế Kiến Trúc - Render Với Sketchup Và Revit', 'Tồn tại', 'LS Tue Aug 01 18:02:54 ICT 2023'),
	('S Sat Aug 06 10:10:57 ICT 2023', 'MS02.png', 'Word Ứng Dụng Văn Phòng Từ Cơ Bản Đến Nâng Cao là cuốn sách tuyệt vời để giúp bạn giải quyết các vấn đề thường gặp phải trong quá trình sử dụng Word từ đơn giản đến phức tạp', 2021, 'Thông tin và truyền thông', 1, 21, 'Nguyễn Quang Vinh', 'Sách Word ĐÀO TẠO TIN HỌC Ứng Dụng Văn Phòng Từ Cơ Bản Đến Nâng Cao', 'Tồn tại', 'LS Tue Aug 03 18:02:54 ICT 2023'),
	('S Sat Aug 07 10:10:57 ICT 2023', 'MS03.png', 'Hiện nay ngôn ngữ lập trình bậc cao Python đang nổi lên như một ngôn ngữ lập trình được sử dụng NHIỀU NHẤT trên thế giới. Điều này được giải thích bằng các lý do sau:', 2022, 'Đại học quốc gia Hà Nội', 1, 15, 'Bùi Việt Hà', 'Python cơ bản', 'Tồn tại', 'LS Tue Aug 02 18:02:54 ICT 2023'),
	('S Sat Aug 08 10:10:57 ICT 2023', 'MS04.png', 'Đây là cuốn sách nâng cao, tiếp theo cuốn Python cơ bản đã được xuất bản cách đây 4 năm. Cuốn sách này đặc biệt dành cho các giáo viên và học sinh cấp THPT đang được học ngôn ngữ lập trình Python theo Chương trình GDPT 2018 mới của Bộ Giáo dục và Đào tạo.', 2021, 'Đại học quốc gia Hà Nội', 2, 8, 'Bùi Việt Hà', 'Python nâng cao', 'Tồn tại', 'LS Tue Aug 02 18:02:54 ICT 2023'),
	('S Sat Aug 09 10:10:57 ICT 2023', 'MS05.png', 'Sách Excel Power Query - Power Pivot tự động hóa dữ liệu báo cáo cơ bản là cuốn sách tuyệt vời để giúp bạn giải quyết các công việc về báo cáo tiện lợi hơn và nhanh chóng', 2021, 'Thông tin và truyền thông', 1, 21, 'Nguyễn Quang Vinh', 'Sách Excel Power Query Và Power Pivot Tự Động Hóa Dữ Liệu Báo Cáo Cơ Bản', 'Tồn tại', 'LS Tue Aug 03 18:02:54 ICT 2023'),
	('S Sat Aug 10 10:10:57 ICT 2023', 'MS06.png', 'Làm phim với PREMIERE PRO CC. Gồm 2 phần với 18 chương', 2021, 'Thanh Niên', 1, 21, 'Nguyễn Đức Phú', 'Làm Phim Với Premiere Pro CC', 'Tồn tại', 'LS Tue Aug 01 18:02:54 ICT 2023'),
	('S Sat Aug 11 10:10:57 ICT 2023', 'MS07.png', 'Nghệ thuật thiết kế Game của tác giả Jesse Schell, giáo sư về công nghệ giải trí của Đại học Carnegie Mellon, CEO của Schell Games,từ lâu đã là một cuốn sách gối đầu giường dành cho những nhà thiết kế game trên thế giới. Những người làm việc trong ngành p', 2022, 'Dân Trí', 2, 10, 'Jesse Schell', 'Nghệ Thuật Thiết Kế Game', 'Tồn tại', 'LS Tue Aug 03 18:02:54 ICT 2023'),
	('S Sat Aug 12 10:10:57 ICT 2023', 'MS08.png', 'I. Đôi điều về tác giả\r\nTôi là NEOS.THÀNH (Nguyễn Văn Thành) – Một lập trình viên Java-Android, tác giả cuốn sách “Lập trình hướng đối tượng Java Core”, CEO của công ty TNHH MTV DV   Giáo Dục Thành Nguyên, đồng thời là mentor tại trường ĐH trực tuyến FUNiX, giảng viên giảng dạy tại cao đẳng nghề PolyTechnic,  công ty phần mềm Luvina và công ty phần mềm FPT.\r\n\r\nII. Quyển sách này nói về điều gì?\r\n- JAVA là ngôn ngữ lập trình rất phổ biến nhất hiện nay, học Lập trình hướng đối tượng JAVA bạn sẽ có rất nhiều hướng đi, từ lập trình Mobile app, Java web, Desktop\r\n  App, Game, và tất cả đều sử dụng nền tảng của JAVA CORE.\r\n- Quyển sách này gồm 22 bài học từ Tư duy Lập trình hướng đối tượng JAVA(Đa hình, kế thừa) đến các đối tượng #JavaCore (String, Array, File), lập trình giao diện Swing.\r\n- Quyển sách Lập trình hướng đối tượng JAVA này sẽ giúp bạn:\r\n    + Đi vào thế giới lập trình hết sức tự nhiên, thân thiện và dễ hiểu - LẬP TRÌNH HƯỚNG ĐỐI TƯỢNG LÀ TƯ DUY GẮN LIỀN VỚI CUỘC SỐNG HẰNG NGÀY\r\n    + Nắm vững được thế nào là tư duy lập trình hướng đối tượng và cách phân tích một bài toán lập trình\r\n    + Hiểu được các khái niệm lập trình Java cơ bản.\r\n    + Thực hành xây dựng được các giao diện phần mềm desktop bằng ngôn ngữ JAVA\r\n=> Sau khi có được nền tảng kiến thức Lập trình hướng đối tượng JAVA bạn có thể tự học các ngôn ngữ lập trình hướng đối tượng khác như C++/C, Python,\r\n\r\nIII. Quyển sách này dành cho ai?\r\n- Là sách tham khảo, hướng dẫn tự học Lập trình hướng đối tượng JAVA bằng ngôn ngữ JAVA Core\r\n- Dành cho người mới bắt đầu học lập trình, sinh viên chưa vững tư duy LTHĐT, Java core\r\n- Dành cho người mất gốc hoặc trái ngành muốn học Lập trình hướng đối tượng JAVA', 2017, 'Lao Động', 1, 2, 'Neos.Thanh', 'Lập trình hướng đối tượng JAVA core', 'Tồn tại', 'LS Tue Aug 02 18:02:54 ICT 2023'),
	('S Sat Aug 13 10:10:57 ICT 2023', 'MS09.png', 'Cung cấp các kiến thức cơ bản về ngôn ngữ lập trình Java, kiến thức về lập trình hướng đối tượng, xử lý biệt lệ, lập trình đa luồng, lập trình from với swing và kết nối cơ sở dữ liệu với Java…', 2022, 'Xây Dựng', 2, 28, 'Phạm Văn Trung', 'Lập Trình Java Cơ Bản', 'Tồn tại', 'LS Tue Aug 02 18:02:54 ICT 2023');

-- Dumping structure for table library_system.thongtincanhan
CREATE TABLE IF NOT EXISTS `thongtincanhan` (
  `maTTCN` varchar(255) NOT NULL,
  `diaChi` varchar(255) NOT NULL,
  `hinh` varchar(255) NOT NULL,
  `soDienThoai` varchar(255) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `trangThai` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`maTTCN`),
  KEY `FK6pgmswu33xdwks5dflkos7fsr` (`email`),
  CONSTRAINT `FK6pgmswu33xdwks5dflkos7fsr` FOREIGN KEY (`email`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table library_system.thongtincanhan: ~4 rows (approximately)
REPLACE INTO `thongtincanhan` (`maTTCN`, `diaChi`, `hinh`, `soDienThoai`, `ten`, `trangThai`, `email`) VALUES
	('TTCN Fri Aug 04 13:20:17 ICT 2023', 'Sài Gòn', 'employee.png', '0387574859', 'Nguyễn Hoà Hưng', 'Tồn tại', 'nguyenhoahung1007@gmail.com'),
	('TTCN Fri Aug 04 13:27:17 ICT 2023', 'Sài Gòn', '1.png', '0375849385', 'admin', 'Tồn tại', 'admin'),
	('TTCN Fri Aug 04 13:37:17 ICT 2023', 'Sài Gòn', 'account.png', '0907890643', 'Nguyễn Lẻ Loi', 'Tồn tại', 'nguyenleloi2k3@gmail.com'),
	('TTCN Fri Aug 04 13:47:17 ICT 2023', 'Sài Gòn', '1.png', '0343704932', 'Đào Đức Dũng', 'Tồn tại', 'daoducdung2000@gmail.com');

-- Dumping structure for table library_system.user
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `trangThai` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table library_system.user: ~4 rows (approximately)
REPLACE INTO `user` (`username`, `password`, `role`, `trangThai`) VALUES
	('admin', '123', 'admin', 'Tồn tại'),
	('daoducdung2000@gmail.com', '123', 'Đọc giả', 'Tồn tại'),
	('nguyenhoahung1007@gmail.com', '123', 'Đọc giả', 'Tồn tại'),
	('nguyenleloi2k3@gmail.com', '123', 'Đọc giả', 'Tồn tại');

-- Dumping structure for table library_system.yeucau
CREATE TABLE IF NOT EXISTS `yeucau` (
  `maYeuCau` varchar(255) NOT NULL,
  `loaiSach` varchar(255) NOT NULL,
  `moTa` varchar(255) NOT NULL,
  `sach` varchar(255) NOT NULL,
  `trangThai` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`maYeuCau`),
  KEY `FKph9gt2wve635p6gr6wvuji9v` (`email`),
  CONSTRAINT `FKph9gt2wve635p6gr6wvuji9v` FOREIGN KEY (`email`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table library_system.yeucau: ~3 rows (approximately)
REPLACE INTO `yeucau` (`maYeuCau`, `loaiSach`, `moTa`, `sach`, `trangThai`, `email`) VALUES
	('YC Fri Aug 04 13:20:17 ICT 2023', 'LS02', 'Sách còn thiếu trong thư viện', 'Lập trình Java Core', 'Tồn tại\r\n\r\n', 'nguyenleloi2k3@gmail.com'),
	('YC Fri Aug 04 14:20:17 ICT 2023', 'LS01', 'Sách còn thiếu trong thư viện', 'Hướng Dẫn Sử Dụng Arduino', 'Tồn tại', 'daoducdung2000@gmail.com'),
	('YC Fri Aug 04 15:20:17 ICT 2023', 'LS03', 'Sách còn thiếu trong thư viện', '150 Thủ Thuật Excel Ứng Dụng Văn Phòng', 'Tồn tại', 'nguyenhoahung1007@gmail.com');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
