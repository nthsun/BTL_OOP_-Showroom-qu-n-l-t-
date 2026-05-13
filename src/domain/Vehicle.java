package domain;
public abstract class Vehicle {
    private String maXe;
    private String bienSo;
    private int soCho;
    private String loai;

    public Vehicle(String maXe, String bienSo, int soCho, String loai) {
        this.maXe = maXe;
        this.bienSo = bienSo;
        this.soCho = soCho;
        this.loai = loai;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public int getSoCho() {
        return soCho;
    }

    public void setSoCho(int soCho) {
        this.soCho = soCho;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
}
