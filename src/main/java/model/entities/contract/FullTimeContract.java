package model.entities.contract;

import java.time.LocalDate;

public class FullTimeContract extends Contract {

    private final float hoursPerDay;
    public FullTimeContract(LocalDate startDate, LocalDate finishDate, double payPerHour) {
        super(startDate, finishDate, payPerHour);
        this.hoursPerDay = 8.0F;
    }

    @Override
    public double calculatePay(double timeDifference) {
        return this.getPayPerHour() * this.getHoursPerDay() * timeDifference;
    }

    public float getHoursPerDay() {
        return hoursPerDay;
    }
}
