package storage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    // Đọc file CSV, trả về danh sách String[]
    public static List<String[]> read(String filePath) {
        List<String[]> data = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return data;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                // tách bằng dấu phẩy
                data.add(line.split(",", -1));
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc CSV: " + e.getMessage());
        }
        return data;
    }

    // Ghi dữ liệu ra file CSV
    public static void write(String filePath, List<String[]> data) {
        try {
            File file = new File(filePath);
            // tạo thư mục cha nếu chưa có
            if (file.getParentFile() != null) file.getParentFile().mkdirs();
            // tạo file nếu chưa có
            if (!file.exists()) file.createNewFile();

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), StandardCharsets.UTF_8))) {
                for (String[] row : data) {
                    bw.write(String.join(",", row));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi ghi CSV: " + e.getMessage());
        }
    }
}
