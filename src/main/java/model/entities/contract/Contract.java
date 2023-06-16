package model.entities.contract;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Contract {

    private final LocalDate startDate;
    private final LocalDate finishDate;
    private final double payPerHour;

    public Contract(LocalDate startDate, LocalDate finishDate, double payPerHour) {
        this.startDate = startDate;
        if (finishDate.isAfter(startDate)){
            this.finishDate = finishDate;
            this.payPerHour = payPerHour;
        }else{
            throw new RuntimeException("Invalid Date");
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public double getPayPerHour() {
        return payPerHour;
    }

    public double calculate(LocalDate fromDate, LocalDate toDate){
        if (toDate.isAfter(fromDate)) {
            if (toDate.isAfter(startDate)  && fromDate.isBefore(finishDate)) {
                if (fromDate.isBefore(startDate)){
                    if (toDate.isAfter(finishDate)) {
                        return calculatePeriod(startDate, finishDate);
                    }
                    else return calculatePeriod(startDate, toDate);

                }
                else {
                    if (toDate.isAfter(finishDate)) {
                        return calculatePeriod(fromDate, finishDate);
                    }
                    else return calculatePeriod(fromDate, toDate);
                }
            }
            else return 0.0;
        }
        else throw new RuntimeException("Invalid date");
    }

    private double calculatePeriod(LocalDate startPeriod, LocalDate endPeriod){
        double timeDifference = ChronoUnit.DAYS.between(startPeriod, endPeriod);
        return calculatePay(timeDifference);
    }

    public abstract double calculatePay(double timeDifference);
}
