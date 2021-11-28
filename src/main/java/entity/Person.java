package entity;

import java.util.Date;
import java.util.List;

public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private int contactedNumber;
    private Date lastContactedDate;
    private Date dateAdded;
    private List<EmailAddress> emailAddresses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getContactedNumber() {
        return contactedNumber;
    }

    public void setContactedNumber(int contactedNumber) {
        this.contactedNumber = contactedNumber;
    }

    public Date getLastContactedDate() {
        return lastContactedDate;
    }

    public void setLastContactedDate(Date lastContactedDate) {
        this.lastContactedDate = lastContactedDate;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public List<EmailAddress> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(List<EmailAddress> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactedNumber=" + contactedNumber +
                ", lastContactedDate=" + lastContactedDate +
                ", dateAdded=" + dateAdded +
                ", emailAddresses=" + emailAddresses +
                '}';
    }
}
