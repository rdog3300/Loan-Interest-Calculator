package com.company;

/**
 * LoanSystem Class
 * Represents a system to handle loan information
 * @author  Ryan Kruszewski
 */
public class LoanSystem {
    /*ATTRIBUTES*/
    double balance;
    double totalPay;
    double interest_rate;
    double monthly_payment;
    int number_payments;
    String Analysis;

    /*CONSTRUCTOR*/

    /**
     * Default Constructor
     */
    public LoanSystem()
    {
        setBalance(0);
        setTotalPay(0);
        setInterest_rate(0);
        setNumber_payments(0);
        Analysis = "";

    }

    /*METHODS*/

    //Setters

    /**
     * Sets the balance of the loan
     * @param balance
     */
    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    private void setTotalPay(double totalPay)
    {
        this.totalPay = totalPay;
    }

    /**
     * Sets the interest rate of the loan
     * @param interest_rate
     */
    public void setInterest_rate(double interest_rate)
    {
        this.interest_rate = interest_rate;
    }

    /**
     * Sets the number of payments
     * @param number_payments The number of payments
     */
    public void setNumber_payments(int number_payments)
    {
        this.number_payments = number_payments;
    }

    /**
     * Sets the monthly payment
     * @param monthly_payment The monthly payment
     */
    public void setMonthly_payment(double monthly_payment)
    {
        this.monthly_payment = monthly_payment;
    }
    //Getters

    /**
     * Returns the balance of the loan
     * @return The balance of the loan
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     * Returns the total pay of the loan
     * @return
     */
    public double getTotalPay()
    {
        return totalPay;
    }

    public double getMonthly_payment(){return  monthly_payment;}
    /**
     * Returns the interest rate of the loan
     * @return The interest rate of the loan
     */
    public double getInterest_rate()
    {
        return interest_rate;
    }

    /**
     * Returns the number of payments of the loan
     * @return The number of payments of the loan
     */
    public int getNumber_payments()
    {
        return number_payments;
    }

    public String getAnalysis()
    {
        return Analysis;
    }

    //STRING DATA GETTERS
    public String getNumber_payments_Str()
    {
        return String.format("%d",getNumber_payments());
    }

    public String getMonthly_payment_Str()
    {
        return String.format("%.2f",getMonthly_payment());
    }






    //Other methods

    private void calculate_TotalPay()
    {
        totalPay = monthly_payment * number_payments;
    }

    private void calculate_MonthlyPayments()
    {
        setMonthly_payment((getBalance()*(getInterest_rate() / 12)) / (1 - Math.pow((1 + (getInterest_rate() / 12.0)), (-1.0 *getNumber_payments()))));
    }

    private void calculate_NumberPayments()
    {
        number_payments = 1 + (int)(-1 * (Math.log(-1 * (getBalance() * (getInterest_rate() / 12)/(getMonthly_payment())) + 1)) / (Math.log(1 + (getInterest_rate() / 12))));
    }

    public void Analyze(boolean hasNumPayments) {

            Analysis += String.format("Loan Balance:  $%.2f\n", getBalance());
            Analysis += String.format("Interest Rate:   %.2f%%\n\n", (getInterest_rate() * 100));
            if(hasNumPayments) {  calculate_MonthlyPayments();}
            else{calculate_NumberPayments();}
            calculate_TotalPay();
            Analysis += String.format("%d Payments of  $%.2f\n", getNumber_payments(), getMonthly_payment());
            Analysis += String.format("Total payment:  $%.2f\n", getTotalPay());
            Analysis += String.format("Total interest paid:  $%.2f\n", (getTotalPay() - getBalance()));
    }

    public void clear()
    {
        setBalance(0.0);
        setInterest_rate(0.0);
        setNumber_payments(0);
        setMonthly_payment(0.0);
        Analysis = "";
    }

}
