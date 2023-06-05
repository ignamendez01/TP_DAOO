import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FullTimeContract extends Contract {

    private final float hoursPerDay;
    public FullTimeContract(Date startDate, Date finishDate, float payPerHour) {
        super(startDate, finishDate, payPerHour);
        this.hoursPerDay = 8.0F;
    }

    @Override
    public float calculate(Date date) {
        if (date.after(this.getStartDate())) {
            long timeDifference;
            if (date.before(this.getFinishDate())) {
                timeDifference = date.getTime() - this.getStartDate().getTime();
            }
            else {
                timeDifference = this.getFinishDate().getTime() - this.getStartDate().getTime();
            }
            return this.getPayPerHour() * this.getHoursPerDay() * TimeUnit.DAYS.convert(timeDifference, TimeUnit.MILLISECONDS);
        }
        else return 0.0F;
    }

    public float getHoursPerDay() {
        return hoursPerDay;
    }
}
