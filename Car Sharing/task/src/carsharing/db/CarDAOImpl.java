package carsharing.db;

import org.h2.jdbcx.JdbcDataSource;

import java.util.List;

public class CarDAOImpl implements CarDAO{
    private final Database db;
    // private static final String CONNECTION_URL = "jdbc:h2:./src/carsharing/db/";

    private static final String CREATE_CARS = "CREATE TABLE IF NOT EXISTS CAR(" +
            "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR(30) UNIQUE NOT NULL," +
            "company_id INTEGER NOT NULL," +
            "CONSTRAINT fk_company_id FOREIGN KEY (company_id) REFERENCES COMPANY(id));";
    private static final String DROP_CARS = "DROP TABLE IF EXISTS CAR;";
    private static final String SELECT_CARS = "SELECT * FROM CAR;";
    private static final String INSERT_CAR = "INSERT INTO CAR (name, company_id) VALUES ('%s', '%d')";

    public CarDAOImpl(String CONNECTION_URL) {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(CONNECTION_URL);

        db = new Database(ds);
    }

    @Override
    public void createTable() {
        db.run(CREATE_CARS);
    }

    @Override
    public void dropTable() {
        db.run(DROP_CARS);
    }

    @Override
    public List<Car> carList() {
        return db.selectCarList(SELECT_CARS);
    }

    @Override
    public void insertCar(String car, int companyId) {
        db.run(String.format(INSERT_CAR, car, companyId));
    }
}
