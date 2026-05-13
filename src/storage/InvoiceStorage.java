package storage;

import domain.Passenger;
import domain.Route;
import domain.Vehicle;
import invoice.Invoice;
import invoice.Ticket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InvoiceStorage {

    private static final String FILE_PATH = "/Users/levanduc/Working/Ads Tech/BTL_OOP/src/invoices.csv";


    public static List<Invoice> readInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        List<String[]> data = CSVUtils.read(FILE_PATH);

        if (data.isEmpty()) {
            return invoices;
        }

        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);
            if (row.length < 17) continue;

            try {
                String maHoaDon = row[0];
                String maVe = row[1];

                Passenger passenger = new Passenger(row[2], row[3]);
                Route route = new Route(row[4], row[5], row[6], new BigDecimal(row[7]));
                Vehicle vehicle = new Vehicle(row[8], row[9], Integer.parseInt(row[10]), row[11]) {};

                int ghe = Integer.parseInt(row[12]);
                LocalDate ngayDi = LocalDate.parse(row[13]);
                boolean daHuy = Boolean.parseBoolean(row[14]);
                BigDecimal tongTien = new BigDecimal(row[15]);
                LocalDateTime ngayLap = LocalDateTime.parse(row[16]);

                Ticket ticket = new Ticket(maVe, passenger, route, vehicle, ghe, ngayDi, daHuy, tongTien);
                Invoice inv = new Invoice(maHoaDon, ticket);

                inv.setNgayLap(ngayLap);
                inv.setTongTien(tongTien);

                invoices.add(inv);
            } catch (Exception e) {
                System.out.println("Lỗi tại dòng " + (i + 1));
            }
        }

        return invoices;
    }

    /** Ghi toàn bộ invoice vào file */
    public static void writeInvoices(List<Invoice> invoices) {
        List<String[]> data = new ArrayList<>();

        // Header
        data.add(new String[]{
                "MaHoaDon", "MaVe", "MaHanhKhach", "HoTen",
                "MaTuyen", "DiemDi", "DiemDen", "GiaVe",
                "MaXe", "BienSo", "SoCho", "Loai",
                "Ghe", "NgayDi", "DaHuy", "TongTien", "NgayLap"
        });

        for (Invoice inv : invoices) {
            Ticket t = inv.getTicket();

            data.add(new String[]{
                    inv.getMaHoaDon(),
                    t.getMaVe(),
                    t.getHanhKhach().getMaHanhKhach(),
                    t.getHanhKhach().getHoTen(),
                    t.getTuyen().getMaTuyen(),
                    t.getTuyen().getDiemDi(),
                    t.getTuyen().getDiemDen(),
                    String.valueOf(t.getTuyen().getGiaVe()),
                    t.getXe().getMaXe(),
                    t.getXe().getBienSo(),
                    String.valueOf(t.getXe().getSoCho()),
                    t.getXe().getLoai(),
                    String.valueOf(t.getGhe()),
                    String.valueOf(t.getNgayDi()),
                    String.valueOf(t.isDaHuy()),
                    String.valueOf(inv.getTongTien()),
                    String.valueOf(inv.getNgayLap())
            });
        }

        CSVUtils.write(FILE_PATH, data);
    }
}
