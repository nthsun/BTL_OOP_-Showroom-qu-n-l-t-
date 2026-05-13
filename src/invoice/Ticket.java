package invoice;

import domain.Passenger;
import domain.Route;
import domain.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Ticket {
    private String maVe;
    private Passenger hanhKhach;
    private Route tuyen;
    private Vehicle xe;
    private int ghe;
    private LocalDate ngayDi;
    private boolean daHuy;
    private BigDecimal gia;

    public Ticket(String maVe, Passenger hanhKhach, Route tuyen, Vehicle xe, int ghe, LocalDate ngayDi, boolean daHuy, BigDecimal gia) {
        this.maVe = maVe;
        this.hanhKhach = hanhKhach;
        this.tuyen = tuyen;
        this.xe = xe;
        this.ghe = ghe;
        this.ngayDi = ngayDi;
        this.daHuy = daHuy;
        this.gia = gia;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public Passenger getHanhKhach() {
        return hanhKhach;
    }

    public void setHanhKhach(Passenger hanhKhach) {
        this.hanhKhach = hanhKhach;
    }

    public Route getTuyen() {
        return tuyen;
    }

    public void setTuyen(Route tuyen) {
        this.tuyen = tuyen;
    }

    public Vehicle getXe() {
        return xe;
    }

    public void setXe(Vehicle xe) {
        this.xe = xe;
    }

    public int getGhe() {
        return ghe;
    }

    public void setGhe(int ghe) {
        this.ghe = ghe;
    }

    public LocalDate getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(LocalDate ngayDi) {
        this.ngayDi = ngayDi;
    }

    public boolean isDaHuy() {
        return daHuy;
    }

    public void setDaHuy(boolean daHuy) {
        this.daHuy = daHuy;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }
}
