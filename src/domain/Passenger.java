package domain;

public class Passenger {

        private String maHanhKhach;
        private String hoTen;

        public Passenger(String maHanhKhach, String hoTen) {
            this.maHanhKhach = maHanhKhach;
            this.hoTen = hoTen;
        }

        public String getMaHanhKhach() {
            return maHanhKhach;
        }

        public void setMaHanhKhach(String maHanhKhach) {
            this.maHanhKhach = maHanhKhach;
        }

        public String getHoTen() {
            return hoTen;
        }

        public void setHoTen(String hoTen) {
            this.hoTen = hoTen;
        }
}
