package model.entities.contract;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FullTimeContract extends Contract {

    private final float hoursPerDay;
    public FullTimeContract(LocalDate startDate, LocalDate finishDate, double payPerHour) {
        super(startDate, finishDate, payPerHour);
        this.hoursPerDay = 8.0F;
    }

    @Override
    public double calculatePeriod(LocalDate startDate, LocalDate endPeriodDate) {
        double timeDifference;
        timeDifference = ChronoUnit.DAYS.between(startDate, endPeriodDate);
        return this.getPayPerHour() * this.getHoursPerDay() * timeDifference;
    }

    @Override
    public void print() {
        System.out.println("Contract: Full time contract");
        System.out.println("Starting date: " + getStartDate().toString());
        System.out.println("Finish date: " + getFinishDate().toString());
        System.out.println("Pay per hour: " + getPayPerHour());
        System.out.println("Hours per day: " + getHoursPerDay());

    }

    public float getHoursPerDay() {
        return hoursPerDay;
    }
}
