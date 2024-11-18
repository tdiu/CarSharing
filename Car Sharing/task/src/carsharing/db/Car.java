package carsharing.db;

import java.util.Objects;

public class Car {
    private int id;
    private String make;
    private int companyId;

    public Car(int id, String make, int companyId) {
        this.id = id;
        this.make = make;
        this.companyId = companyId;
    }

    public Car(int id, String make) {
        this.id = id;
        this.make = make;
        companyId = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return id + ". " + make ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;
        return id == car.id && companyId == car.companyId && Objects.equals(make, car.make);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(make);
        result = 31 * result + companyId;
        return result;
    }
}
