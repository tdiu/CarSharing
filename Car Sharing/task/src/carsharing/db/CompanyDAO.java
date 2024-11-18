package carsharing.db;

import java.util.List;

public interface CompanyDAO {
    public List<Company> companyList();
    public void insertCompany(String company);
    public void createTable();
    public void dropTable();
}
