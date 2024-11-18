package carsharing.db;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Database {

    private final DataSource dataSource;

    public Database(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private <T> List<T> selectAll(String query, RowMapper<T> rowMapper) {
        List<T> results = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                results.add(rowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<Company> selectList(String query) {
        return selectAll(query, rs ->
                new Company(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
    }

    public List<Car> selectCarList(String query) {
        return selectAll(query, rs ->
                new Car(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("company_id")
                ));
    }

    public List<Customer> selectCustomerList(String query) {
        return selectAll(query, rs ->
                new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("rented_car_id") == 0 ? null : rs.getInt("rented_car_id")
                ));
    }

    public void run (String query) {
        try (Connection con = dataSource.getConnection();
            Statement stmt = con.createStatement()
        ) {
            stmt.executeUpdate(query);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
