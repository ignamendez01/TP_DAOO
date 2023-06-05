import java.util.Date;

public abstract class Contract {

    private Date startDate;
    private Date finishDate;
    private float payPerHour;

    public Contract(Date startDate, Date finishDate, float payPerHour) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.payPerHour = payPerHour;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public float getPayPerHour() {
        return payPerHour;
    }

    public abstract float calculate(Date date);
}
