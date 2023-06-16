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
    public double calculatePay(double timeDifference) {
        double totalDaysContract = ChronoUnit.DAYS.between(this.getStartDate(), this.getFinishDate());
        return this.getPayPerHour() * ((float) timeDifference / (float) totalDaysContract) * totalHours;
    }

    public double getTotalHours() {
        return totalHours;
    }
}
