package Package;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Person implements Serializable {
    private String namePatient;
    private String surnamePatient;
    private String patronymicPatient;
    private int cardNumber;
    private String work;
    private double priceWork;
    private Date dateCompletionWork;
    private String payment;
    private double duty;

    public Person(String namePatient, String surnamePatient, String patronymicPatient, int cardNumber, String work, double priceWork, LocalDate dateCompletionWork, String payment, double duty) {
        this.setNamePatient(namePatient);
        this.setSurnamePatient(surnamePatient);
        this.setPatronymicPatient(patronymicPatient);
        this.setCardNumber(cardNumber);
        this.setWok(work);
        this.setPriceWork(priceWork);
        this.setDateCompletionWork(Date.valueOf(dateCompletionWork));
        this.setPayment(payment);
        this.setDuty(duty);
    }

    //проверка правильности имени;
    public void setNamePatient(String namePatient) {
        this.namePatient = namePatient.strip();
        for (int i = 0; i < this.namePatient.length(); i++) {
            if (!Character.isAlphabetic(this.namePatient.charAt(i))) {
                this.namePatient = "Incorrect name";
            }
        }
        this.namePatient = ("" + this.namePatient.charAt(0)).toUpperCase() + this.namePatient.substring(1).toLowerCase();
    }

    //проверка правильности фамилии;
    public void setSurnamePatient(String surnamePatient) {
        this.surnamePatient = surnamePatient.strip();
        for (int i = 0; i < this.surnamePatient.length(); i++) {
            if (!Character.isAlphabetic(this.surnamePatient.charAt(i))) {
                this.surnamePatient = "Incorrect surname";
            }
        }
        this.surnamePatient = ("" + this.surnamePatient.charAt(0)).toUpperCase() + this.surnamePatient.substring(1).toLowerCase();
    }

    //проверка правильности отчества;
    public void setPatronymicPatient(String patronymicPatient) {
        this.patronymicPatient = patronymicPatient.strip();
        for (int i = 0; i < this.patronymicPatient.length(); i++) {
            if (!Character.isAlphabetic(this.patronymicPatient.charAt(i))) {
                this.patronymicPatient = "Incorrect patronymic";
            }
        }
        this.patronymicPatient = ("" + this.patronymicPatient.charAt(0)).toUpperCase() + this.patronymicPatient.substring(1).toLowerCase();
    }

    //проверка номера карточки пациента;
    public void setCardNumber(int cardNumber) {
        if (this.cardNumber < 0) {
            System.out.println("The number cannot be negative.");
        } else {
            this.cardNumber = cardNumber;
        }
    }

    //процедура;
    private void setWok(String work) {
        this.work = work.strip();
    }

    //Стоимость работы;
    private void setPriceWork(double priceWork) {
        if (priceWork < 0) {
            this.patronymicPatient = "Price cannot be negative.";
        } else {
            this.priceWork = priceWork;
        }
    }

    //    дата выполнения работы;
    public void setDateCompletionWork(Date dateCompletionWork) {
        //Год должен быть больше или равер теперешнему;
        if (dateCompletionWork.getYear() < LocalDate.now().getYear()) {
            this.dateCompletionWork = dateCompletionWork;
            this.dateCompletionWork = new Date(0, 0, 0);
        } else {
            this.dateCompletionWork = Util.getCorrectDate(dateCompletionWork);
        }
    }

    //   оплата;
    public void setPayment(String payment) {
        this.payment = payment.strip();
    }

    //Долги;
    public void setDuty(double duty) {
        this.duty = duty;
    }

    public String getNamePatient() {
        return namePatient;
    }

    public String getSurnamePatient() {
        return surnamePatient;
    }

    public String getPatronymicPatient() {
        return patronymicPatient;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getWork() {
        return work;
    }

    public double getPriceWork() {
        return priceWork;
    }

    public Date getDateCompletionWork() {
        return dateCompletionWork;
    }

    public String getPayment() {
        return payment;
    }

    public double getDuty() {
        return duty;
    }

    @Override
    public String toString() {
        return "Person {" + "\n" +
                " Name = '" + getNamePatient() + '\'' + "\n" +
                " Surname = '" + getSurnamePatient() + '\'' + "\n" +
                " Patronymic = '" + getPatronymicPatient() + '\'' + "\n" +
                " Card number = '" + getCardNumber() + '\'' + "\n" +
                " Type of work = '" + getWork() + '\'' + "\n" +
                " Price of work = '" + getPriceWork() + '\'' + "\n" +
                " Date completion work = " + getDateCompletionWork() + "\n" +
                " Payment = '" + getPayment() + '\'' + "\n" +
                " Duty = '" + getDuty() + '\'' + "\n" +
                '}';
    }
}