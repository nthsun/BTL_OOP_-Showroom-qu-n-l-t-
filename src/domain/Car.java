package domain;

public class Car extends Vehicle {
    public Car(String maXe, String bienSo, int soCho, String loai) {
        super(maXe, bienSo, soCho, loai);
    }
    public void hienThiThongTin() {
        System.out.println("Thông tin xe:");
        System.out.println("- Mã xe: " + getMaXe());
        System.out.println("- Biển số: " + getBienSo());
        System.out.println("- Số chỗ: " + getSoCho());
        System.out.println("- Loại: " + getLoai());
    }

    @Override
    public String toString() {
        return "Car{" +
                "maXe='" + getMaXe() + '\'' +
                ", bienSo='" + getBienSo() + '\'' +
                ", soCho=" + getSoCho() +
                ", loai='" + getLoai() + '\'' +
                '}';
    }


}
