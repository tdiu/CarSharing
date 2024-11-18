package carsharing;

import carsharing.db.CompanyDAOImpl;
import carsharing.db.Database;
import carsharing.db.UIController;


public class Main {
    private static final String CONNECTION_URL = "jdbc:h2:./src/carsharing/db/carsharing";

    public static void main(String[] args) {
        String databaseFile = "carsharing";

        UIController uiController= new UIController(CONNECTION_URL);
        uiController.start();
    }
}

