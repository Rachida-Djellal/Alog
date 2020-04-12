package models;

public class Client  {

    private final int id ;
    private final String firstName ;
    private final String lastName ;
    private String phone ;
    private String email ;
    private String information ;
    private String address ;



    public Client(int id, String firstName, String lastName, String address,String phone, String email, String information) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.information = information;
    }


    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getInformation() {
        return information;
    }


    // private String address ;



    int getId() { return id; }


   public String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }
}
