package app;

import domain.Car;
import domain.Route;
import domain.Vehicle;
import domain.Passenger;
import invoice.Invoice;
import invoice.InvoiceManager;
import invoice.Ticket;
import repo.Repository;
import service.InMemoryCarRepository;
import storage.CarStorage;
import storage.InvoiceStorage;
import storage.RouteStorage;
import exception.DuplicateMaException;
import exception.InvalidGiaVeException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static Repository<Car> carRepo = new InMemoryCarRepository();


    private static List<Route> routeList;
    private static InvoiceManager invoiceManager = new InvoiceManager();

    public static void main(String[] args) {
        List<Car> loadCars = CarStorage.readCars();
        for (Car c : loadCars) {
            try {
                carRepo.add(c);
            } catch (Exception ignored) {}
        }
        System.out.println("Đã load " + carRepo.findAll().size() + " xe từ file CSV.");



        routeList = RouteStorage.readRoute();
        List<Invoice> invoicesFromFile = InvoiceStorage.readInvoices();
        for (Invoice inv : invoicesFromFile) {
            invoiceManager.add(inv);
        }

        System.out.println("Đã load " + routeList.size() + " tuyến từ file CSV.");
        System.out.println("Đã load " + invoiceManager.findAll().size() + " hóa đơn từ file CSV.");

        while (true) {
            System.out.println("\n===== MENU CHÍNH =====");
            System.out.println("1. Quản lý ô tô");
            System.out.println("2. Quản lý tuyến");
            System.out.println("3. Bán hàng / Hóa đơn");
            System.out.println("5. Lưu xe ra file");
            System.out.println("6. Lưu tuyến ra file");
            System.out.println("7. Lưu hóa đơn ra file");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            String line = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    menuQuanLyXe();
                    break;
                case 2:
                    menuQuanLyTuyen();
                    break;
                case 3:
                    menuQuanLyHoaDon();
                    break;

                case 4:
                    CarStorage.writeCars(carRepo.findAll());
                    System.out.println("Dữ liệu xe đã được lưu ra file CSV!");
                    break;
                case 5:
                    RouteStorage.writeRoute(routeList);
                    System.out.println("Dữ liệu tuyến đã được lưu ra file CSV!");
                    break;
                case 6:
                    InvoiceStorage.writeInvoices(invoiceManager.findAll());
                    System.out.println("Dữ liệu hóa đơn đã được lưu ra file CSV");
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void menuQuanLyXe() {
        while (true) {
            System.out.println("===== QUẢN LÝ Ô TÔ =====");
            System.out.println("1. Thêm xe");
            System.out.println("2. Sửa xe");
            System.out.println("3. Xóa xe theo mã");
            System.out.println("4. Tìm kiếm xe theo mã");
            System.out.println("5. Hiển thị danh sách xe");
            System.out.println("6. Trở về menu chính");
            System.out.print("Chọn: ");
            String line = sc.nextLine();
            switch (line) {
                case "1": themXe(); break;
                case "2": suaXe(); break;
                case "3": xoaXe(); break;
                case "4": timXeTheoMa(); break;
                case "5": hienThiXe(); break;
                case "6": return;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void themXe() {
        try {
            System.out.print("Nhập mã xe: ");
            String ma = sc.nextLine().trim();
            if (carRepo.findByMa(ma).isPresent())
                throw new DuplicateMaException("Mã xe đã tồn tại!");
            System.out.print("Nhập biển số: ");
            String bienSo = sc.nextLine().trim();
            System.out.print("Nhập số chỗ: ");
            int soCho = Integer.parseInt(sc.nextLine().trim());
            if (soCho <= 0) throw new Exception("Số chỗ phải > 0");
            System.out.print("Nhập loại xe: ");
            String loai = sc.nextLine().trim();
            Car car = new Car(ma, bienSo, soCho, loai);
            carRepo.add(car);
            CarStorage.writeCars(new ArrayList<>(carRepo.findAll()));
            System.out.println("Thêm xe thành công!");
        }
        catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }


    private static void suaXe() {
        System.out.print("Nhập mã xe cần sửa: ");
        String ma = sc.nextLine();

        Optional<Car> opt = carRepo.findByMa(ma);
        if (opt.isEmpty()) {
            System.out.println("Không tìm thấy xe!");
            return;
        }

        Car old = opt.get();

        System.out.print("Biển số (" + old.getBienSo() + "): ");
        String bienSo = sc.nextLine();
        if (bienSo.isEmpty()) bienSo = old.getBienSo();

        System.out.print("Số chỗ (" + old.getSoCho() + "): ");
        String soChoStr = sc.nextLine();
        int soCho = soChoStr.isEmpty() ? old.getSoCho() : Integer.parseInt(soChoStr);

        System.out.print("Loại (" + old.getLoai() + "): ");
        String loai = sc.nextLine();
        if (loai.isEmpty()) loai = old.getLoai();

        Car updated = new Car(ma, bienSo, soCho, loai);

        try {
            carRepo.update(updated);
            CarStorage.writeCars(carRepo.findAll());
            System.out.println("Cập nhật thành công!");
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        }
    }


    private static void xoaXe() {
        System.out.print("Nhập mã xe cần xóa: ");
        String ma = sc.nextLine().trim();

        try {
            Car removed = carRepo.deleteByMa(ma);
            CarStorage.writeCars(carRepo.findAll());
            System.out.println("Đã xóa xe: " + removed.getMaXe());
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }



    private static void timXeTheoMa() {
        System.out.print("Nhập mã xe cần tìm: ");
        String ma = sc.nextLine().trim();

        var opt = carRepo.findByMa(ma);
        if (opt.isEmpty()) {
            System.out.println("Không tìm thấy xe!");
            return;
        }

        Car c = opt.get();
        System.out.printf("%-10s %-12s %-8s %-10s%n", "Mã xe", "Biển số", "Số chỗ", "Loại");
        System.out.println("----------------------------------------");
        System.out.printf("%-10s %-12s %-8d %-10s%n",
                c.getMaXe(), c.getBienSo(), c.getSoCho(), c.getLoai());
    }


    private static void hienThiXe() {
        List<Car> list = carRepo.findAll();
        if (list.isEmpty()) {
            System.out.println("Danh sách xe trống!");
            return;
        }

        System.out.printf("%-10s %-12s %-8s %-10s%n", "Mã xe", "Biển số", "Số chỗ", "Loại");
        System.out.println("----------------------------------------");

        for (Car c : list) {
            System.out.printf("%-10s %-12s %-8d %-10s%n",
                    c.getMaXe(), c.getBienSo(), c.getSoCho(), c.getLoai());
        }
    }


    private static void menuQuanLyTuyen() {
        while (true) {
            System.out.println("\n===== QUẢN LÝ TUYẾN =====");
            System.out.println("1. Thêm tuyến");
            System.out.println("2. Hiển thị danh sách tuyến");
            System.out.println("3. Trở về menu chính");
            System.out.print("Chọn: ");
            String line = sc.nextLine();
            switch (line) {
                case "1": themTuyen(); break;
                case "2": hienThiTuyen(); break;
                case "3": return;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void themTuyen() {
        try {
            System.out.print("Nhập mã tuyến: ");
            String ma = sc.nextLine().trim();
            for (Route r : routeList) if (r.getMaTuyen().equalsIgnoreCase(ma))
                throw new DuplicateMaException("Mã tuyến đã tồn tại!");

            System.out.print("Nhập điểm đi: ");
            String diemDi = sc.nextLine().trim();
            System.out.print("Nhập điểm đến: ");
            String diemDen = sc.nextLine().trim();
            System.out.print("Nhập giá vé: ");
            BigDecimal giaVe = new BigDecimal(sc.nextLine().trim());
            if (giaVe.compareTo(BigDecimal.ZERO) < 0) throw new InvalidGiaVeException("Giá vé phải >= 0");

            routeList.add(new Route(ma, diemDi, diemDen, giaVe));

            System.out.println("Thêm tuyến thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static void hienThiTuyen() {
        if (routeList.isEmpty()) { System.out.println("Danh sách tuyến trống!"); return; }
        System.out.printf("%-10s %-15s %-15s %-10s%n", "Mã tuyến", "Điểm đi", "Điểm đến", "Giá vé");
        System.out.println("----------------------------------------------------");
        for (Route r : routeList)
            System.out.printf("%-10s %-15s %-15s %-10s%n", r.getMaTuyen(), r.getDiemDi(), r.getDiemDen(), r.getGiaVe());
    }

//
    private static void menuQuanLyHoaDon() {
        while (true) {
            System.out.println("\n===== QUẢN LÝ HÓA ĐƠN =====");
            System.out.println("1. Thêm hóa đơn");
            System.out.println("2. Hiển thị danh sách hóa đơn");
            System.out.println("3. Lưu hóa đơn ra file CSV");
            System.out.println("4. Tải hóa đơn từ file CSV");
            System.out.println("5. Trở về menu chính");
            System.out.println("0. Thoát chương trình");
            System.out.print(" Chọn: ");

            String chon = sc.nextLine();
            switch (chon) {
                case "1": themHoaDon();break;
                case "2": hienThiDanhSachHoaDon(); break;
                case "3":
                    InvoiceStorage.writeInvoices(invoiceManager.findAll());
                    System.out.println("Lưu hóa đơn thành công!");
                    break;
                case "4":
                    invoiceManager = new InvoiceManager();
                    List<Invoice> fromFile = InvoiceStorage.readInvoices();
                    for (Invoice inv : fromFile) invoiceManager.add(inv);
                    System.out.println("Tải hóa đơn thành công!");
                    break;
                case "5": return;
                case "0":
                    System.out.println("Thoát chương trình.");
                    System.exit(0);
                    break;
                default: System.out.println(" Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void themHoaDon() {
        try {
            System.out.print("Nhập mã hóa đơn: ");
            String maHD = sc.nextLine().trim();
            boolean exists = invoiceManager.findAll().stream()
                    .anyMatch(inv -> inv.getMaHoaDon().equalsIgnoreCase(maHD));
            if (exists) { System.out.println("Mã hóa đơn đã tồn tại!"); return; }

            System.out.print("Nhập mã vé: "); String maVe = sc.nextLine().trim();
            System.out.print("Nhập mã hành khách: "); String maHK = sc.nextLine().trim();
            System.out.print("Nhập họ tên hành khách: "); String hoTen = sc.nextLine().trim();
            System.out.print("Nhập mã tuyến: "); String maTuyen = sc.nextLine().trim();
            System.out.print("Nhập điểm đi: "); String diemDi = sc.nextLine().trim();
            System.out.print("Nhập điểm đến: "); String diemDen = sc.nextLine().trim();
            System.out.print("Nhập giá vé: "); BigDecimal giaVe = new BigDecimal(sc.nextLine().trim());
            System.out.print("Nhập mã xe: "); String maXe = sc.nextLine().trim();
            System.out.print("Nhập biển số: "); String bienSo = sc.nextLine().trim();
            System.out.print("Nhập số chỗ: "); int soCho = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Nhập loại xe: "); String loai = sc.nextLine().trim();
            System.out.print("Nhập số ghế: "); int ghe = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Nhập ngày đi (yyyy-MM-dd): "); LocalDate ngayDi = LocalDate.parse(sc.nextLine().trim());
            System.out.print("Vé đã hủy chưa? (true/false): "); boolean daHuy = Boolean.parseBoolean(sc.nextLine().trim());

            Passenger passenger = new Passenger(maHK, hoTen);
            Route route = new Route(maTuyen, diemDi, diemDen, giaVe);
            Vehicle vehicle = new Vehicle(maXe, bienSo, soCho, loai){};
            Ticket ticket = new Ticket(maVe, passenger, route, vehicle, ghe, ngayDi, daHuy, giaVe);
            Invoice invoice = new Invoice(maHD, ticket);

            invoiceManager.add(invoice);
            System.out.println("Thêm hóa đơn thành công!");
        }
        catch (Exception e) {
            System.out.println("Lỗi khi thêm hóa đơn: " + e.getMessage());
        }
    }

    private static void hienThiDanhSachHoaDon() {
        List<Invoice> list = invoiceManager.findAll();
        if (list.isEmpty()) {
            System.out.println("Danh sách hóa đơn trống!");
            return;
        }

        System.out.printf("%-10s %-10s %-15s %-15s %-10s%n",
                "Mã HĐ", "Mã Vé", "Khách hàng", "Tổng tiền", "Ngày lập");
        System.out.println("----------------------------------------------------------");

        for (Invoice inv : list) {
            System.out.printf("%-10s %-10s %-15s %-15s %-10s%n",
                    inv.getMaHoaDon(),
                    inv.getTicket().getMaVe(),
                    inv.getTicket().getHanhKhach().getHoTen(),
                    inv.getTongTien(),
                    inv.getNgayLap() != null ? inv.getNgayLap().toLocalDate() : "null");
        }
    }
}
