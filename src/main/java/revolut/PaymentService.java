package revolut;

public class PaymentService {
    private String serviceName;
    private double limit;

    public PaymentService(String name){
        this.serviceName = name;
        this.limit = 0.0;
    }

    public String getType() {
        return serviceName;
    }

    public void setLimit(double balance) {
        this.limit = balance;
    }

    public boolean checkTransactionIsValid(double amount) {
        return this.limit >= amount;
    }

    public void charge(double amount) {
        this.limit -= amount;
    }
}
