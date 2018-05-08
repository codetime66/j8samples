/*
 */
package sample;

/**
 *
 * @author codetime
 */
class Invoice {
   private double amount;
   private String customer;
   private Payment payment;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
    
    public Payment getPayment(){
        return payment;
    }
    
    public void setPayment(String type){
        this.payment = new Payment();
        payment.setType(type);
    }
}
