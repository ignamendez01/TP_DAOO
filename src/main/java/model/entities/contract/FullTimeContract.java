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
    public double calculate(LocalDate upToDate) { //Cambiar nombres de las variables y del metodo para representar que es lo que hace
        if (upToDate.isAfter(this.getStartDate())) {
            double timeDifference;
            if (upToDate.isBefore(this.getFinishDate())) {
                timeDifference = ChronoUnit.DAYS.between(this.getStartDate(), upToDate);
            } else {
                timeDifference = ChronoUnit.DAYS.between(this.getStartDate(), this.getFinishDate());
            }
            return this.getPayPerHour() * this.getHoursPerDay() * timeDifference;
        } else {
            return 0.0F;
        }
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
