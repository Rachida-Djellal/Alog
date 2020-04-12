package models;


import java.util.Date;

public class Appointment {

    private  long id ;
    private  Client client ;
    private Date time ;



    private String object ;


    public Appointment(long id, Client client, Date time , String object) {
        this.id = id;
        this.client = client;
        this.time = time;
        this.object = object ;
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Date getTime() {
        return time;
    }

    public String getObject() {
        return object;
    }
/*  public Appointment(Client client , Date time) {
        this.client = client ;
        this.time  = time ;

    }
     public String getObject() {
        return object;
    }
*/
}
