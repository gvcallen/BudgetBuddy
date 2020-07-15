import java.time.LocalDate;

public class Transaction
{
    private double amount;
    private String location;
    private LocalDate date;

    public void setAmount(double amount)
    {
        this.amount = amount;
    }
    public double getAmount()
    {
        return this.amount;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }
    public String getLocation()
    {
        return this.location;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }
    public LocalDate getDate()
    {
        return this.date;
    }
    



}
