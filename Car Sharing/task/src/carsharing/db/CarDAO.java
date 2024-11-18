package carsharing.db;

import java.util.List;

public interface CarDAO {
    public List<Car> carList();
    public void insertCar(String car, int companyId);
    public void createTable();
    public void dropTable();
}
