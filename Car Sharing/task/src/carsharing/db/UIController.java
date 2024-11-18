package carsharing.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UIController {
    Scanner scanner = new Scanner(System.in);
    CompanyCarManager companyCarManager;
    List<Car> rentedCars = new ArrayList<>();

    public UIController(String CONNECTION_URL) {
        companyCarManager = new CompanyCarManager(CONNECTION_URL);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Log in as a manager");
            System.out.println("2. Log in as a customer");
            System.out.println("3. Create a customer");
            System.out.println("0. Exit");

            int mainInput = Integer.parseInt(scanner.nextLine());
            switch (mainInput) {
                case 1:
                    managerMenu();
                    break;
                case 2:
                    showCustomerList();
                    break;
                case 3:
                    createCustomer();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
        System.exit(0);
    }

    private void managerMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Company list");
            System.out.println("2. Create a company");
            System.out.println("0. Back");

            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    managerCompanyList();
                    break;
                case 2:
                    createCompany();
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }
    }

    private void customerMenu(Customer customer) {
        boolean exit = false;

        while (!exit) {
            Customer cust = companyCarManager.getCustomers().stream().filter(i -> i.getId() == customer.getId()).findFirst().orElse(null);
            Integer val = Optional.ofNullable(cust).map(Customer::getRentedCarId).orElse(null);
            System.out.println("1. Rent a car");
            System.out.println("2. Return a rented car");
            System.out.println("3. My rented car");
            System.out.println("0. Back");

            int input = Integer.parseInt(scanner.nextLine());


            switch (input) {
                case 1:
                    if (val != null) {
                        System.out.println("You've already rented a car!");
                        break;
                    }
                    customerCompanyList(customer);
                    break;
                case 2:
                    if (val == null) {
                        System.out.println("You didn't rent a car!");
                        break;
                    }
                    int carId = cust.getRentedCarId();
                    companyCarManager.returnCar(carId);
                    rentedCars.remove(companyCarManager.getCars().stream()
                            .filter(i -> i.getId() == carId).findFirst()
                            .orElse(null));
                    System.out.println("You've returned a rented car!");
                    break;
                case 3:
                    if (val == null) {
                        System.out.println("You didn't rent a car!");
                        break;
                    }
                    Car rentedCar = companyCarManager.getCars().stream()
                            .filter(i -> i.getId() == cust.getRentedCarId())
                            .findFirst().orElse(null);
                    Company rentalCompany = companyCarManager.getCompanies().stream()
                            .filter(i -> i.getId() == rentedCar.getCompanyId())
                            .findFirst().orElse(null);
                    System.out.println("Your rented car:\n" + rentedCar.getMake());
                    System.out.println("Company:\n" + rentalCompany.getCompanyName());
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }
    }

    private void showCustomerList() {
        List<Customer> customerList = companyCarManager.getCustomers();
        if (customerList.isEmpty()) {
            System.out.println("The customer list is empty!");
        } else {
            boolean exit = false;
            while (!exit) {
                System.out.println("Choose a customer:");
                customerList.forEach(System.out::println);
                System.out.println("0. Back");

                int input = Integer.parseInt(scanner.nextLine());
                int index = input > customerList.size() ? - 1 : input  == 0 ? 0 : 1;

                switch (index) {
                    case 1:
                        customerMenu(customerList.get(input - 1));
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid input. Try again.");
                }
            }
        }
    }

    private void managerCompanyList() {
        List<Company> companyList = companyCarManager.getCompanies();
        if (companyList.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            boolean exit = false;
            while (!exit) {
                System.out.println("Choose a company: ");
                companyList.forEach(System.out::println);
                System.out.println("0. Back");

                int input = Integer.parseInt(scanner.nextLine());
                int index = input > companyList.size() ? - 1 : input  == 0 ? 0 : 1;

                switch (index) {
                    case 1:
                        showCarMenu(companyList.get(input - 1));
                        exit = true;
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid input. Try again.");
                        break;
                }
            }
        }
    }

    private void customerCompanyList(Customer customer) {
        List<Company> companyList = companyCarManager.getCompanies();
        if (companyList.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            boolean exit = false;
            while (!exit) {
                System.out.println("Choose a company: ");
                companyList.forEach(System.out::println);
                System.out.println("0. Back");

                int input = Integer.parseInt(scanner.nextLine());
                int index = input > companyList.size() ? - 1 : input  == 0 ? 0 : 1;

                switch (index) {
                    case 1:
                        showRentalCars(companyList.get(input - 1), customer);
                        exit = true;
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid input. Try again.");
                        break;
                }
            }
        }
    }

    private void showCarMenu(Company company) {
        boolean exit = false;
        while (!exit) {
            System.out.printf("'%s' company:\n", company.getCompanyName());
            System.out.println("1. Car list");
            System.out.println("2. Create a car");
            System.out.println("0. Back");

            int input = Integer.parseInt(scanner.nextLine());
            switch(input) {
                case 1:
                    showCars(company);
                    break;
                case 2:
                    createCar(company);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }

    private void showRentalCars(Company company, Customer customer) {
        boolean exit = false;
        while (!exit) {
            List<Car> filteredCars = companyCarManager.getCars().stream()
                    .filter(i -> !rentedCars.contains(i) && i.getCompanyId() == company.getId())
                    .toList();

            if (filteredCars.isEmpty()) {
                System.out.printf("No available cars in the %s company.\n", company.getCompanyName());
                exit = true;
                break;
            }

            System.out.println("Choose a car:");
            for (int i = 1; i <= filteredCars.size(); i++) {
                System.out.printf("%d. %s\n", i, filteredCars.get(i - 1).getMake());
            }
            System.out.println("0. Back");

            int input = Integer.parseInt(scanner.nextLine());

            if (input == 0) {
                exit = true;
                break;
            }
            if (input > filteredCars.size()) {
                System.out.println("Invalid input. Try again.");
                break;
            } else {
                rentedCars.add(filteredCars.get(input - 1));
                companyCarManager.rentCar(filteredCars.get(input - 1).getId(), customer.getId() );
                System.out.printf("You rented '%s'\n", filteredCars.get(input - 1).getMake());
                break;
            }
        }
    }

    private void createCompany() {
        System.out.println("Enter the company name: ");
        String newCompany = scanner.nextLine();
        companyCarManager.insertCompany(newCompany);
        System.out.println("The company was created!");
    }

    private void showCars(Company company) {
        List<Car> cars = companyCarManager.getCars().stream().filter(i -> i.getCompanyId() == company.getId()).toList();
        if (cars.isEmpty()) {
            System.out.println("The car list is empty!");
        } else {
            System.out.println("Car list:");
            for (int i = 0; i < cars.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, cars.get(i).getMake());
            }
        }
    }

    private void createCar(Company company) {
        System.out.println("Enter the car name: ");
        String newCar = scanner.nextLine();
        companyCarManager.insertCar(newCar, company.getId());
        System.out.println("The car was added!");
    }

    private void createCustomer() {
        System.out.println("Enter the customer name:");
        String newCustomer = scanner.nextLine();
        companyCarManager.insertCustomer(newCustomer);
        System.out.println("The customer was added!");
    }

}


