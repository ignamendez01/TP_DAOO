package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FullTimeContract extends Contract {

    private final float hoursPerDay;
    public FullTimeContract(LocalDate startDate, LocalDate finishDate, float payPerHour) {
        super(startDate, finishDate, payPerHour);
        this.hoursPerDay = 8.0F;
    }

    @Override
    public float calculate(LocalDate date) {
        if (date.isAfter(this.getStartDate())) {
            long timeDifference;
            if (date.isBefore(this.getFinishDate())) {
                timeDifference = ChronoUnit.DAYS.between(this.getStartDate(), date);
            } else {
                timeDifference = ChronoUnit.DAYS.between(this.getStartDate(), this.getFinishDate());
            }
            return this.getPayPerHour() * this.getHoursPerDay() * timeDifference;
        } else {
            return 0.0F;
        }
    }

    public float getHoursPerDay() {
        return hoursPerDay;
    }
}
