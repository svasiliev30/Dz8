package dao;



import Person.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepository {

    private final String jdbcUrl = "jdbc:sqlite:C:\\work\\learn programmer\\data base\\sql_lite_dz.db";

    /**
     * Takes an information of table.Create new table.
     * @param informationTable
     * @return
     */
    public boolean createTable(String informationTable) {

        String sql = informationTable;

        try(Connection connection = DriverManager.getConnection(jdbcUrl);
                Statement statement = connection.createStatement();) {
            int affectedRows = statement.executeUpdate(sql);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * Takes an information of person and name table. Added this information to table.
     * @param person
     * @return
     */
    public boolean addPerson (Person person){

       try (Connection connection = DriverManager.getConnection(jdbcUrl);
               Statement statement = connection.createStatement();){

           String sql = String.format("INSERT INTO person ('id', 'name', 'age', 'city') VALUES (%s, '%s', %s, '%s')",
                   person.getId(), person.getName(), person.getAge(), person.getCity());

           boolean hasResultSet = statement.execute(sql);
           int affectedRows = statement.getUpdateCount();
           System.out.println( "affectedRows = " + affectedRows);

       }catch (SQLException e) {
           return false;
       }
        return true;
    }

    /**
     * Takes an age of person and id.Change Age on new age.
     * @param id
     * @param Age
     * @return
     */
    public boolean changeAge (int id, int Age) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl);
                Statement statement = connection.createStatement();){

           int affectedRows = statement.executeUpdate("UPDATE person SET age = " + Age + " WHERE id = " + id +";");
           System.out.println( "affectedRows = " + affectedRows);
            if (affectedRows == 0 ){
                return false;
            }
        }catch (SQLException e){
            return false;
        }
    return true;
    }

    /**
     * Takes a name table and delete his.
     * @param nameTable
     * @return
     */
    public boolean deleteTable(String nameTable){

        String sqlNameTable = nameTable;

       try (Connection connection = DriverManager.getConnection(jdbcUrl);
               Statement statement = connection.createStatement();){
           statement.executeUpdate(sqlNameTable);
       } catch (SQLException e) {
           e.printStackTrace();
           return false;
       }
       return true;
    }

    /**
     * Read information from database. 
     * @return
     */
    public List<Person> readAllPersons()
    {
        final String readAllPersonsQuery = "select * from person";

        try(Connection connection = DriverManager.getConnection(jdbcUrl);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(readAllPersonsQuery))
        {
         List<Person> personRead = new ArrayList<>();

            while (resultSet.next())
            {
                Person localPerson = new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("city"),
                        resultSet.getInt("age"));
                personRead.add(localPerson);
            }
            return personRead;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

