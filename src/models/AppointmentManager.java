package models;

import com.sun.istack.internal.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class AppointmentManager extends DataBaseManager {

    // The date formats
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private final static SimpleDateFormat DATE_FORMAT_DETAILS = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    // The columns in the database table for each table
    private final static String[] appointmentColumns = {"id" , "appointment_date", "id_client" , "object"} ;
    private final static String[] clientColumns = {"id_client", "first_name" , "last_name" , "address" , "phone" , "email" , "information"} ;

    // These maps are for not duplication of the instance of clients or appointments



    /**
     * Because it's singleton
     */
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
        String sql = "SELECT a.id , a.appointment_date , a.id_client  , a.object , c.first_name , c.last_name , c.address , c.phone , c.email , c.information FROM  " +
                    appointmentTable  +" AS a JOIN " + clientTable +
                    " AS c ON c.id = a.id_client ORDER BY a.appointment_date ASC" ;

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
        return result;

    }

/**
 * This function returns a list of appointment for the client passing on parameter sorted by the date
 * */
    public ArrayList<Appointment> getByClient(Client client){


       String sql = " WITH appointment_query AS (SELECT * FROM appointment WHERE id_client = "+ client.getId()+ ")" +
                    " SELECT * FROM appointment_query AS q JOIN client ON client.id = q.id_client"+
                    " ORDER BY q.appointment_date ASC";

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


    public void update( Appointment appointment){

        String sql = "UPDATE " + appointmentTable + " AS a SET " + appointmentColumns[1] + " = '" + DATE_FORMAT_DETAILS.format(appointment.getTime()) +
                     "' WHERE a.id = " + appointment.getId();
        try {

            System.out.println(sql);
            query(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Appointment> getTodayAppointment(){
        String sql ="SELECT * FROM appointment as a where strftime('%Y/%m/%d', "+appointmentColumns[1]+") =strftime('%Y/%m/%d', date('now'))" ;
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
     * fetch an appointment from a resultSet  , using the appointmentColumns ( it is declared above )
     * I don't use all the columns -I talk about id_client because I'm passing the Client as a parameter to the function
     * also the name -id_client- on the clientColumns doesn't exist on the clientTable it's named id ( this may make confuse )
     * */
     @NotNull
    private Appointment fetchAppointment(ResultSet resultSet ,  Client client) throws SQLException, ParseException {

        int id = resultSet.getInt(appointmentColumns[0]);
        if(appointmentInInstance.containsKey(id)){
            return  appointmentInInstance.get(id);
        }
        String time = resultSet.getString(appointmentColumns[1]);
        Date date =DATE_FORMAT_DETAILS.parse(time);
        String object =resultSet.getString(appointmentColumns[3]);
        Appointment appointment = new Appointment(id,client,date , object);
         appointmentInInstance.put(id, appointment);
        return  appointment;
    }


    /**
     * fetch a client from a resultSet  , using the clientColumns ( it is declared above )
     * */

    @NotNull
    private Client fetchClient(ResultSet resultSet ) throws SQLException {
        int id_client = resultSet.getInt(clientColumns[0]);

        if (clientInInstance.containsKey(id_client)){
            return clientInInstance.get(id_client);
        }
        String firstName = resultSet.getString(clientColumns[1]);
        String lastName = resultSet.getString(clientColumns[2]);
        String address = resultSet.getString(clientColumns[3]);
        String phone = resultSet.getString(clientColumns[4]);
        String email = resultSet.getString(clientColumns[5]);
        String information = resultSet.getString(clientColumns[6]);
        Client client =new Client(id_client,firstName,lastName , address , phone , email , information );
        clientInInstance.put(id_client,client);
        return client;
    }


    public void create(@NotNull Appointment appointment){

        // I have to change this query , but now it works
        String sql ="INSERT INTO " + appointmentTable +" ('appointment_date','id_client' , 'object') VALUES ('"+DATE_FORMAT_DETAILS.format(appointment.getTime())+"','"+appointment.getClient().getId()+"','"+appointment.getObject() +"')";
        try {
            System.out.println(sql);
            super.insert(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }


    public ArrayList<Appointment> getAppointmentByDay(Date day){

      String sql = " WITH dat as (SELECT * FROM appointment  WHERE strftime('%Y-%m-%d' ,appointment.appointment_date) = '" + DATE_FORMAT.format(day)  +"')"+
                   "  SELECT * FROM dat JOin client on client.id = dat.id_client" ;

        ArrayList<Appointment> result = new ArrayList<>();
        try (ResultSet resultSet = super.query(sql)) {

            while (resultSet.next()) {
                Client client = fetchClient(resultSet);
                Appointment appointment = fetchAppointment(resultSet, client);
                result.add(appointment);
            }
        }
        catch (SQLException | ParseException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        return result;

    }



    public  void delete(Appointment appointment){
        String sql ="DELETE FROM " + appointmentTable + "WHERE id = '"+appointment.getId() + "'";
        try {
            query(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}