package storage;

import domain.Car;

import java.util.ArrayList;
import java.util.List;

public class CarStorage {

    private static final String FILE_PATH = "/Users/levanduc/Working/Ads Tech/BTL_OOP/src/cars.csv";

    public static List<Car> readCars() {
        ArrayList<Car> cars = new ArrayList<>();
        List<String[]> data = CSVUtils.read(FILE_PATH);
        if (data.isEmpty()) return cars;

        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);
            if (row.length >= 4) {
                try {
                    cars.add(new Car(
                            row[0],
                            row[1],
                            Integer.parseInt(row[2]),
                            row[3]
                    ));
                } catch (NumberFormatException e) {
                    System.out.println(" Lỗi số chỗ tại dòng " + (i + 1) + ": " + row[2]);
                }
            }
        }

        if (cars.isEmpty()) {
            cars.add(new Car("C01", "30A-12345", 16, "Limousine"));
            cars.add(new Car("C02", "30B-67890", 29, "Bus"));
            writeCars(cars);
        }

        return cars;
    }

    public static void writeCars(List<Car> cars) {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"MaXe", "BienSo", "SoGhe", "Loai"});
        for (Car c : cars) {
            data.add(new String[]{
                    c.getMaXe(),
                    c.getBienSo(),
                    String.valueOf(c.getSoCho()),
                    c.getLoai()
            });
        }
        CSVUtils.write(FILE_PATH, data);
    }
}
