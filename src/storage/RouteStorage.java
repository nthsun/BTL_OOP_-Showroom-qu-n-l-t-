package storage;

import domain.Route;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RouteStorage {
    private static final String FILE_PATH = "/Users/levanduc/Working/Ads Tech/BTL_OOP/src/route.csv";
    public static List<Route> readRoute(){
        ArrayList<Route> route = new ArrayList<>();
        List<String[]> data = CSVUtils.read(FILE_PATH);
        if(data.isEmpty()){
            return route;
        }
        for (int i = 1; i<data.size(); i++){
            String [] row = data.get(i);
            if(row.length >=4){
                try {
                    route.add(new Route(row[0],row[1],row[2],new BigDecimal(row[3])));
                }
                catch (NumberFormatException e){
                    System.out.println("Lỗi tại dòng " + (i + 1) + ": " + row[3]);
                }
            }
        }
        if (route.isEmpty()) {
            route.add(new Route("T01", "Hà Nội", "Thanh Hóa", BigDecimal.valueOf(150000.000)));
            route.add(new Route("T02", "Hà Nội", "Hải Phòng", BigDecimal.valueOf(150000.000)));
            writeRoute(route);
        }
        return route;
    }

    public static void writeRoute(List<Route> route){
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"MaTuyen", "DiemDi", "DiemDen", "GiaVe"});
        for (Route r: route){
            data.add(new String[]{
                r.getMaTuyen(),
                r.getDiemDen(),
                r.getDiemDi(),
                String.valueOf(r.getGiaVe())
            });
        }
        CSVUtils.write(FILE_PATH, data);

    }
}
