package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientManager  extends DataBaseManager {



    private static ClientManager  singleInstance  = null;

    private  ClientManager(){};

    public static ClientManager getInstance() {
        if (singleInstance == null){
            singleInstance = new ClientManager();
        }
        return singleInstance;
    }

    public void insertClient( Client client){

        // I have to change this query after , but now it works

        // Client table is defined in the DataBaseManager class
        String sql ="INSERT INTO " + clientTable +" ('first_name','last_name') VALUES ('"+client.getFirstName()+"','"+client.getLastName()+"')";
        try {
            super.insert(sql);

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Client> getAll(){

             // Client table is defined in the DataBaseManager class
            String sql = "SELECT * FROM  " + clientTable ;

            ArrayList<Client> result = new ArrayList<>();

            // query function is declared in the DataBaseManage Class
            try (ResultSet resultSet = super.query(sql)) {

                while (resultSet.next()){
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    int id = resultSet.getInt("id");
                    Client client = new Client(id, firstName,lastName);
                    result.add(client);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

            return result ;

        }

}

