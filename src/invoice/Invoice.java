
package invoice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Invoice {
    private String maHoaDon;
    private Ticket ticket;
    private LocalDateTime ngayLap;
    private BigDecimal tongTien;

    public Invoice(String maHoaDon, Ticket ticket) {
        this.maHoaDon = maHoaDon;
        this.ticket = ticket;
        this.ngayLap = LocalDateTime.now();
        this.tongTien = ticket.getGia();
    }

    public Invoice(String maHoaDon, Ticket ticket, LocalDateTime ngayLap, BigDecimal tongTien) {
        this.maHoaDon = maHoaDon;
        this.ticket = ticket;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public LocalDateTime getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDateTime ngayLap) {
        this.ngayLap = ngayLap;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "maHoaDon='" + maHoaDon + '\'' +
                ", ticket=" + ticket +
                ", ngayLap=" + ngayLap +
                ", tongTien=" + tongTien +
                '}';
    }
}
