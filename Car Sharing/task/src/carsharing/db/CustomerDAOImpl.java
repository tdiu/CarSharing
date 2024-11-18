package carsharing.db;

import org.h2.jdbcx.JdbcDataSource;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO{

    private final Database db;
    private static final String CREATE_CUSTOMER = "CREATE TABLE IF NOT EXISTS CUSTOMER(" +
            "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR(30) UNIQUE NOT NULL," +
            "rented_car_id INTEGER," +
            "CONSTRAINT fk_car_id FOREIGN KEY (rented_car_id) REFERENCES CAR(id));";
    private static final String DROP_CUSTOMERS = "DROP TABLE IF EXISTS CUSTOMER;";
    private static final String SELECT_CUSTOMERS = "SELECT * FROM CUSTOMER;";
    private static final String INSERT_CUSTOMER = "INSERT INTO CUSTOMER (name) VALUES ('%s')";
    private static final String RETURN_CAR = "UPDATE CUSTOMER SET rented_car_id=NULL WHERE rented_car_id=%d";
    private static final String RENT_CAR = "UPDATE CUSTOMER SET rented_car_id='%d' WHERE id=%d";

    public CustomerDAOImpl(String CONNECTION_URL) {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(CONNECTION_URL);

        db = new Database(ds);
    }

    @Override
    public void createTable() {
        db.run(CREATE_CUSTOMER);
    }

    @Override
    public void dropTable() {
        db.run(DROP_CUSTOMERS);
    }

    @Override
    public List<Customer> customerList() {
        return db.selectCustomerList(SELECT_CUSTOMERS);
    }

    @Override
    public void insertCustomer(String name) {
        db.run(String.format(INSERT_CUSTOMER, name));
    }

    @Override
    public void returnCar(int id) {
        db.run(String.format(RETURN_CAR, id));
    }

    @Override
    public void rentCar(int carId, int customerId) {
        db.run(String.format(RENT_CAR, carId, customerId));
    }
}
