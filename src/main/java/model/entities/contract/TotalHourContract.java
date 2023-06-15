package model.entities.contract;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class TotalHourContract extends Contract {

    private final double totalHours;
    public TotalHourContract(LocalDate startDate, LocalDate finishDate, double payPerHour, double totalHours) {
        super(startDate, finishDate, payPerHour);
        this.totalHours = totalHours;
    }

    @Override
    public double calculatePeriod(LocalDate startDate, LocalDate endPeriodDate) {
        double timeDifference = ChronoUnit.DAYS.between(startDate, endPeriodDate);
        double totalDaysContract = ChronoUnit.DAYS.between(this.getStartDate(), this.getFinishDate());
        return this.getPayPerHour() * ((float) timeDifference / (float) totalDaysContract) * totalHours;
    }

    public double getTotalHours() {
        return totalHours;
    }

    @Override
    public void print() {
        System.out.println("Contract: Total hour contract");
        System.out.println("Starting date: " + getStartDate().toString());
        System.out.println("Finish date: " + getFinishDate().toString());
        System.out.println("Pay per hour: " + getPayPerHour());
        System.out.println("Total hours: " + getTotalHours());
    }
}
