# BTL OOP - Hệ thống Quản lý Showroom và Vé xe

Dự án Bài tập lớn môn Lập trình hướng đối tượng (OOP) xây dựng chương trình quản lý thông tin xe, lộ trình và hóa đơn bán vé.

## 🚀 Tính năng chính
*   **Quản lý thực thể:** Điều hành thông tin về Xe (`Car`, `Vehicle`), Tài xế (`Driver`), Hành khách (`Passenger`) và Lộ trình (`Route`).
*   **Xử lý nghiệp vụ:** Quản lý hóa đơn (`Invoice`), quản lý vé (`Ticket`) và tính toán doanh thu thông qua `InvoiceManager`.
*   **Xử lý ngoại lệ:** Hệ thống có các Exception riêng để kiểm soát dữ liệu đầu vào như:
    *   `DuplicateMaException`: Trùng mã định danh.
    *   `InvalidGiaVeException`: Giá vé không hợp lệ.
    *   `NotFoundException`: Không tìm thấy đối tượng.
*   **Lưu trữ:** Hỗ trợ đọc/ghi dữ liệu từ file CSV để bảo toàn dữ liệu sau khi đóng chương trình.

## 📂 Cấu trúc mã nguồn
Dựa trên kiến trúc phân lớp:
*   `src/app`: Chứa điểm khởi đầu của ứng dụng (`Main.java`).
*   `src/domain`: Chứa các lớp đối tượng (POJO).
*   `src/exception`: Các lớp xử lý lỗi tùy chỉnh.
*   `src/invoice`: Logic liên quan đến thanh toán và vé.
*   `src/storage` & `src/service`: (Dựa trên cấu trúc file trước đó) Xử lý lưu trữ CSV và Repo.

## 🛠 Cài đặt và Sử dụng
1.  **Yêu cầu:** Máy tính đã cài JDK (8 hoặc 11+) và một IDE (IntelliJ IDEA, Eclipse, VS Code).
2.  **Cài đặt:**
    ```bash
    git clone https://github.com
    ```
3.  **Chạy ứng dụng:**
    *   Mở thư mục `BTL_OOP` bằng IDE của bạn.
    *   Tìm file `src/app/Main.java`.
    *   Run file `Main.java` để bắt đầu sử dụng menu điều khiển trên Terminal.

## 📝 Thông tin tác giả
*   **Họ và tên:** Nguyễn Thị Hoa
*   **Lớp:** CNTT 18-11
