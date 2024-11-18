package carsharing.db;

import org.h2.jdbcx.JdbcDataSource;

import java.util.List;


public class CompanyCarManager {
    private CompanyDAOImpl companyDAO;
    private CarDAOImpl carDAO;
    private CustomerDAOImpl customerDAO;

    public CompanyCarManager(String CONNECTION_URL) {
        this.companyDAO = new CompanyDAOImpl(CONNECTION_URL);
        this.carDAO = new CarDAOImpl(CONNECTION_URL);
        this.customerDAO = new CustomerDAOImpl(CONNECTION_URL);

        companyDAO.createTable();
        carDAO.createTable();
        customerDAO.createTable();
    }

    public List<Company> getCompanies() {
            return companyDAO.companyList();
    }

    public void insertCompany(String company) {
        companyDAO.insertCompany(company);
    }

    public List<Car> getCars() {
        return carDAO.carList();
    }

    public void insertCar(String car, int companyId) {
        carDAO.insertCar(car, companyId);
    }

    public List<Customer> getCustomers() {
        return customerDAO.customerList();
    }

    public void insertCustomer(String customer) {
        customerDAO.insertCustomer(customer);
    }

    public void returnCar(int id) {
        customerDAO.returnCar(id);
    }

    public void rentCar(int carId, int customerId) {
        customerDAO.rentCar(carId, customerId);
    }
}
