package models;

public class Client  {

    private  int id ;
    private final String firstName ;
    private final String lastName ;
    private String phone ;
    private String email ;
    private String information ;
    private String address ;



    public Client(int id, String firstName, String lastName, String address, String phone, String email, String information) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.information = information;
    }


   public Client ( String firstName, String lastName, String address, String phone, String email, String information) {
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



   public int getId() { return id; }



   public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return this.getFirstName()+this.getLastName()+this.getAddress()+this.getPhone()+this.getEmail()+this.getInformation();
    }
}

