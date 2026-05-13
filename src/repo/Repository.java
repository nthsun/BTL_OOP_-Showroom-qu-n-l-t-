package repo;

import java.util.List;
import java.util.Optional;
public interface Repository<T>{
    void add(T t);
    void update(T t);
    T deleteByMa(String ma);
    Optional<T> findByMa(String ma);
    List<T> findAll();
}
