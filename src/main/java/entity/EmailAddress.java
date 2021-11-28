package entity;

public class EmailAddress {

    private int id;
    private int personId;
    private String emailAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "EmailAddress{" +
                "id=" + id +
                ", personId=" + personId +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
