package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Route {
    private String maTuyen;
    private String diemDi;
    private String diemDen;
    private BigDecimal giaVe;

    public Route(String maTuyen, String diemDi, String diemDen, BigDecimal giaVe) {
        this.maTuyen = maTuyen;
        this.diemDi = diemDi;
        this.diemDen = diemDen;
        this.giaVe = giaVe.setScale(2, RoundingMode.HALF_UP);
    }

    public String getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(String maTuyen) {
        this.maTuyen = maTuyen;
    }

    public String getDiemDi() {
        return diemDi;
    }

    public void setDiemDi(String diemDi) {
        this.diemDi = diemDi;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public void setDiemDen(String diemDen) {
        this.diemDen = diemDen;
    }

    public BigDecimal getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(BigDecimal giaVe) {
        this.giaVe = giaVe.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Route{" +
                "maTuyen='" + maTuyen + '\'' +
                ", diemDi='" + diemDi + '\'' +
                ", diemDen='" + diemDen + '\'' +
                ", giaVe=" + giaVe +
                '}';
    }

}
