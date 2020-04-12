package models;


import java.util.Date;

public class Appointment {

    private  long id ;
    private  Client client ;
    private Date time ;


    public Appointment(long id, Client client, Date time) {
        this.id = id;
        this.client = client;
        this.time = time;
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
/*  public Appointment(Client client , Date time) {
        this.client = client ;
        this.time  = time ;

    }
*/
}
