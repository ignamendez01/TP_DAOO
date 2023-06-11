package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class TotalHourContract extends Contract {

    private double totalHours;
    public TotalHourContract(LocalDate startDate, LocalDate finishDate, double payPerHour, double totalHours) {
        super(startDate, finishDate, payPerHour);
        this.totalHours = totalHours;
    }

    @Override
    public double calculate(LocalDate date) {
        if (date.isAfter(this.getStartDate())) {
            if (date.isBefore(this.getFinishDate())) {
                double timeDifference = ChronoUnit.DAYS.between(this.getStartDate(), date);
                double totalDaysContract = ChronoUnit.DAYS.between(this.getStartDate(), this.getFinishDate());
                return this.getPayPerHour() * ((float) timeDifference / (float) totalDaysContract) * totalHours;
            } else {
                return this.totalHours * this.getPayPerHour();
            }
        } else {
            return 0.0F;
        }
    }
}
