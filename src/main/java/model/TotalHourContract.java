package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class TotalHourContract extends Contract {

    private float totalHours;
    public TotalHourContract(LocalDate startDate, LocalDate finishDate, float payPerHour, float totalHours) {
        super(startDate, finishDate, payPerHour);
        this.totalHours = totalHours;
    }

    @Override
    public float calculate(LocalDate date) {
        if (date.isAfter(this.getStartDate())) {
            if (date.isBefore(this.getFinishDate())) {
                long timeDifference = ChronoUnit.DAYS.between(this.getStartDate(), date);
                long totalDaysContract = ChronoUnit.DAYS.between(this.getStartDate(), this.getFinishDate());
                return this.getPayPerHour() * ((float) timeDifference / (float) totalDaysContract) * totalHours;
            } else {
                return this.totalHours * this.getPayPerHour();
            }
        } else {
            return 0.0F;
        }
    }
}
