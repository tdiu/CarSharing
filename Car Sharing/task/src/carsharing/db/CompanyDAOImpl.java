package carsharing.db;

import java.util.List;
import org.h2.jdbcx.JdbcDataSource;

public class CompanyDAOImpl implements CompanyDAO {

    private final Database db;
    private static final String CONNECTION_URL = "jdbc:h2:./src/carsharing/db/";

    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS COMPANY(" +
            "id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL," +
            "name VARCHAR(30) UNIQUE NOT NULL);";
    private static final String DROP_DB = "DROP TABLE IF EXISTS COMPANY;";
    private static final String DROP_CARS = "DROP TABLE IF EXISTS CAR;";
    private static final String SELECT_ALL = "SELECT * FROM COMPANY;";
    private static final String INSERT_DATA = "INSERT INTO COMPANY (name) VALUES ('%s')";


    public CompanyDAOImpl(String CONNECTION_URL) {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(CONNECTION_URL);

        db = new Database(ds);
    }

    @Override
    public void createTable() {
        db.run(CREATE_DB);
    }

    @Override
    public void dropTable() {
        db.run(DROP_DB);
    }

    @Override
    public void insertCompany(String company) {
        db.run(String.format(INSERT_DATA, company));
    }

    @Override
    public List<Company> companyList() {
        return db.selectList(SELECT_ALL);
    }
}
