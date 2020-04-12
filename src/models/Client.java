package models;

public class Client  {


    private final int id ;
    private final String firstName ;
    private final String lastName ;
    // private String address ;


    Client(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    int getId() { return id; }


   public String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }
}
