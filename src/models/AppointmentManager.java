package models;

import com.sun.istack.internal.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AppointmentManager extends DataBaseManager {

    // The date format
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    private final static String[] appointmentColumns = {"id" , "date", "id_client"} ;

    private final static String[] clientColumns = {"id_client", "first_name" , "last_name"} ;

    private static AppointmentManager  singleInstance  = null;

    private  AppointmentManager(){};

    public static AppointmentManager getInstance() {
        if (singleInstance == null){
            singleInstance = new AppointmentManager();
        }
        return singleInstance;
    }


    /**
     * This function returns all the appointment sorted by the date
     * */
    public ArrayList<Appointment> getAll(){

        // Client table is defined in the DataBaseManager class
        String sql = "SELECT a.id , a.date , a.id_client , c.first_name , c.last_name FROM  " + appointmentTable  +" AS a JOIN " + clientTable + " AS c ON c.id = a.id_client ORDER BY a.date ASC" ;

        ArrayList<Appointment> result = new ArrayList<>();

        // query function is declared in the DataBaseManage Class
        try (ResultSet resultSet = super.query(sql)) {

            while (resultSet.next()){
                Client client = this.fetchClient(resultSet);
                Appointment appointment = fetchAppointment(resultSet,client);
                result.add(appointment);
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return result ;
    }

/**
 * This function returns a list of appointment for the client passing on parameter sorted by the date
 * */
    public ArrayList<Appointment> getByClient(Client client){


       String sql = " WITH appointment_query AS (SELECT * FROM appointment WHERE id_client = "+ client.getId()+ ")" +
                    " SELECT * FROM appointment_query AS q JOIN client ON client.id = q.id_client"+
                    " ORDER BY q.date ASC";

        ArrayList<Appointment> result = new ArrayList<>();

        // query function is declared in the DataBaseManage Class
        try (ResultSet resultSet = super.query(sql)) {

            while (resultSet.next()){
                Appointment appointment = fetchAppointment(resultSet,client);
                result.add(appointment);
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return result ;
    }






    /**
     * fetch an appointment from a resultSet  , using the appointmentColumns ( it is declared above )
     * I don't use all the columns -I talk about id_client because I'm passing the Client as a parameter to the function
     * also the name -id_client- on the clientColumns doesn't exist on the clientTable it's named id ( this may make confuse )
     * */
    @NotNull
    private Appointment fetchAppointment(ResultSet resultSet ,  Client client) throws SQLException, ParseException {

        int id = resultSet.getInt(appointmentColumns[0]);
        String time = resultSet.getString(appointmentColumns[1]);
        Date date =dateFormat.parse(time);

        return new Appointment(id,client,date);
    }



    /**
     * fetch a client from a resultSet  , using the clientColumns ( it is declared above )
     * */
    @NotNull
    private Client fetchClient(ResultSet resultSet ) throws SQLException {
        int id_client = resultSet.getInt(clientColumns[0]);
        String firstName = resultSet.getString(clientColumns[1]);
        String lastName = resultSet.getString(clientColumns[2]);

        return new Client(id_client,firstName,lastName);
    }



    public void create(@NotNull Appointment appointment){

        // I have to change this query , but now it works
        String sql ="INSERT INTO " + appointmentTable +" ('date','id_client') VALUES ('"+dateFormat.format(appointment.getTime())+"','"+appointment.getClient().getId()+"')";
        try {
            super.insert(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }




}
