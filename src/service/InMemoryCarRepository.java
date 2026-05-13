package service;

import domain.Car;
import exception.DuplicateMaException;
import exception.NotFoundException;
import repo.Repository;
import java.util.*;

public class InMemoryCarRepository implements Repository<Car> {
    private final ArrayList<Car> list = new ArrayList<>();
    private final HashMap<String, Car> map = new HashMap<>();

    @Override
    public void add(Car c) {
        if (map.containsKey(c.getMaXe()))
            throw new DuplicateMaException("Mã xe đã tồn tại: " + c.getMaXe());

        map.put(c.getMaXe(), c);
        list.add(c);
    }

    @Override
    public void update(Car c) {
        if (!map.containsKey(c.getMaXe()))
            throw new NotFoundException("Không tìm thấy xe: " + c.getMaXe());

        map.put(c.getMaXe(), c);
        int idx = indexOf(c.getMaXe());
        if (idx >= 0) list.set(idx, c);
    }

    @Override
    public Car deleteByMa(String ma) {
        Car removed = map.remove(ma);
        if (removed == null)
            throw new NotFoundException("Không tìm thấy xe để xóa: " + ma);

        list.removeIf(x -> x.getMaXe().equals(ma));
        return removed;
    }

    @Override
    public Optional<Car> findByMa(String ma) {
        return Optional.ofNullable(map.get(ma));
    }

    @Override
    public List<Car> findAll() {
        return Collections.unmodifiableList(list);
    }

    private int indexOf(String ma) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMaXe().equals(ma))
                return i;
        }
        return -1;
    }
}
