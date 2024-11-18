package carsharing.db;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> customerList();
    public void insertCustomer(String name);
    public void returnCar(int id);
    public void rentCar(int carId, int customerId);
    public void createTable();
    public void dropTable();
}
