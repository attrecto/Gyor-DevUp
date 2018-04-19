package main.java.gyor.meetup;

import main.kotlin.gyor.meetup.Money;

import java.util.Objects;

public class JavaMoney {

    private int amount;
    private String currency;

    public JavaMoney(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "JavaMoney{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaMoney money = (JavaMoney) o;
        return amount == money.amount &&
                Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
//
//    public static void main(String[] args) {
//        Money money = new Money(20, "$");
//        money.getAmount()
//    }

}
