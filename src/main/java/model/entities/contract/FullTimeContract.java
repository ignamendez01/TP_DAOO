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

    @Override
    public void print() {  //TODO: LLevar a UI
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
