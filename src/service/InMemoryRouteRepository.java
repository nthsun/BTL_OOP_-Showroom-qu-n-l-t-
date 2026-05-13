package service;

import domain.Route;
import exception.DuplicateMaException;
import exception.NotFoundException;
import repo.Repository;

import java.util.*;

public class InMemoryRouteRepository implements Repository<Route> {
    private final ArrayList<Route> list = new ArrayList<>();
    private final HashMap<String, Route> map = new HashMap<>();
    @Override
    public void add(Route r) {
        if (map.containsKey(r.getMaTuyen()))
            throw new DuplicateMaException("Trùng mã tuyến: " + r.getMaTuyen());
        list.add(r);
        map.put(r.getMaTuyen(), r);
    }

    @Override
    public void update(Route r) {
        if (!map.containsKey(r.getMaTuyen()))
            throw new NotFoundException("Không tìm thấy tuyến: " + r.getMaTuyen());
        map.put(r.getMaTuyen(), r);
        int index = indexOf(r.getMaTuyen());
        if (index >= 0) list.set(index, r);
    }

    @Override
    public Route deleteByMa(String ma) {
        Route removed = map.remove(ma);
        if (removed == null)
            throw new NotFoundException("Không tìm thấy tuyến để xóa: " + ma);
        list.removeIf(r -> r.getMaTuyen().equals(ma));
        return removed;
    }

    @Override
    public Optional<Route> findByMa(String ma) {
        return Optional.ofNullable(map.get(ma));
    }

    @Override
    public List<Route> findAll() {
        return Collections.unmodifiableList(list);
    }

    private int indexOf(String ma) {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).getMaTuyen().equals(ma)) return i;
        return -1;
    }

}
