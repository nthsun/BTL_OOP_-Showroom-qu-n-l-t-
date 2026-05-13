package invoice;

import java.util.*;
import exception.DuplicateMaException;
import exception.NotFoundException;

public class InvoiceManager {
    private final List<Invoice> list = new ArrayList<>();
    private final Map<String, Invoice> map = new HashMap<>();

    public void add(Invoice invoice) {
        if (map.containsKey(invoice.getMaHoaDon()))
            throw new DuplicateMaException("Hóa đơn " + invoice.getMaHoaDon() + " đã tồn tại!");
        list.add(invoice);
        map.put(invoice.getMaHoaDon(), invoice);
    }

    public Invoice findById(String id) {
        Invoice inv = map.get(id);
        if (inv == null) throw new NotFoundException("Không tìm thấy hóa đơn " + id);
        return inv;
    }

    public List<Invoice> findAll() {
        return Collections.unmodifiableList(list);
    }

    public boolean remove(String id) {
        Invoice inv = map.remove(id);
        if (inv != null) {
            list.remove(inv);
            return true;
        }
        return false;
    }
}
