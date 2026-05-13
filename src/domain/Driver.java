package domain;

public class Driver {
        private String maTaiXe;
        private String hoTen;
        private String bangLai;
        private int namKinhNghiem;

        public Driver(String maTaiXe, String hoTen, String bangLai, int namKinhNghiem) {
            this.maTaiXe = maTaiXe;
            this.hoTen = hoTen;
            this.bangLai = bangLai;
            this.namKinhNghiem = namKinhNghiem;
        }

        public String getMaTaiXe() {
            return maTaiXe;
        }

        public void setMaTaiXe(String maTaiXe) {
            this.maTaiXe = maTaiXe;
        }

        public String getHoTen() {
            return hoTen;
        }

        public void setHoTen(String hoTen) {
            this.hoTen = hoTen;
        }

        public String getBangLai() {
            return bangLai;
        }

        public void setBangLai(String bangLai) {
            this.bangLai = bangLai;
        }

        public int getNamKinhNghiem() {
            return namKinhNghiem;
        }

        public void setNamKinhNghiem(int namKinhNghiem) {
            this.namKinhNghiem = namKinhNghiem;
        }
}
