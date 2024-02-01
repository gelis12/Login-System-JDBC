package DB;

import Constants.ConstanteUtile;

import java.sql.*;


public class DatabaseConnection {
    //registering new user to database >> true = register success >> false = register fail
    public static boolean register(String username, String password){
        //checking if the username already exist in database
        try {
            if (!checkUser(username)) {
                Connection connection = DriverManager.getConnection(ConstanteUtile.dbURL, ConstanteUtile.dbUsername, ConstanteUtile.dbPassword);
                PreparedStatement addNewUser = connection.prepareStatement("INSERT INTO " + ConstanteUtile.dbTableName + "(username,password)"+"VALUES (?,?)");
                addNewUser.setString(1,username);
                addNewUser.setString(2,password);

                addNewUser.executeUpdate();
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }

    //checking if username already exist in database
    public static boolean checkUser(String username){
        try {
            Connection connection = DriverManager.getConnection(ConstanteUtile.dbURL,ConstanteUtile.dbUsername,ConstanteUtile.dbPassword);
            PreparedStatement checkUserExist = connection.prepareStatement(
                    "SELECT * FROM "+ConstanteUtile.dbTableName+" WHERE username = ?");
            checkUserExist.setString(1,username);

            ResultSet resultSet = checkUserExist.executeQuery();

            if(!resultSet.isBeforeFirst()){
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    //validate if in db exist or not a pair of username and pass same as what the user introduce

    public static boolean validateUserPass(String username, String password){
        try {
            Connection connection = DriverManager.getConnection(ConstanteUtile.dbURL,ConstanteUtile.dbUsername,ConstanteUtile.dbPassword);
            PreparedStatement checkPairUsernameAndPassword = connection.prepareStatement(
                    "SELECT * FROM "+ConstanteUtile.dbTableName+" WHERE USERNAME=? AND PASSWORD=?");
            checkPairUsernameAndPassword.setString(1,username);
            checkPairUsernameAndPassword.setString(2,password);

            ResultSet resultSet = checkPairUsernameAndPassword.executeQuery();

            if(!resultSet.isBeforeFirst()){
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
