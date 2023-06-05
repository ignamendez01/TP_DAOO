import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TotalHourContract extends Contract {

    private float totalHours;
    public TotalHourContract(Date startDate, Date finishDate, float payPerHour, float totalHours) {
        super(startDate, finishDate, payPerHour);
        this.totalHours = totalHours;
    }

    @Override
    public float calculate(Date date) {
        if (date.after(this.getStartDate())) {
            if (date.before(this.getFinishDate())) {
                long timeDifference = TimeUnit.DAYS.convert(date.getTime() - this.getStartDate().getTime(), TimeUnit.MILLISECONDS);
                long totalDaysContract = TimeUnit.DAYS.convert(this.getFinishDate().getTime() - this.getStartDate().getTime(), TimeUnit.MILLISECONDS);
                return this.getPayPerHour() * ((float) timeDifference/ (float) totalDaysContract) * totalHours;
            }
            else {
                return this.totalHours * this.getPayPerHour();
            }
        }
        else return 0.0F;
    }
}
