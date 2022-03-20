package testPerson;

import Person.Person;
import dao.ProductRepository;
import org.junit.Test;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestCreateTable {


    /**
     * Create table with id, name, age, city.
     * @throws SQLException
     */
    @Test
    public void createTable()  {
        String sql = "CREATE TABLE person (" +
                "id VARCHAR(50)," +
                "name VARCHAR(50)," +
                "age VARCHAR(50)," +
                "city VARCHAR(100)" +
                ");";

        ProductRepository repository = new ProductRepository();
        repository.createTable(sql);
    }

    /**
     * Added information to the table. Table person creating/
     * @throws SQLException
     */
    @Test
    public void addInformation() throws SQLException {

            ProductRepository repository = new ProductRepository();

            List<Person> list = new ArrayList<>();
            list.add(new Person(1, "Дурин1", "Кхазад-Дум", 160 ));
            list.add(new Person(2, "Дурин6", "Кхазад-Дум", 267));
            list.add(new Person(3, "Наин1", "Кхазад-Дум", 180));
            list.add(new Person(4, "Трайн1", "Кхазад-Дум", 234));
            list.add(new Person(5, "Торин1", "Кхазад-Дум", 254));

            for (int i = 0; i < list.size(); i++) {
               repository.addPerson(list.get(i));
            }
    }

    /**
     * Read information for table.
     * @throws SQLException
     */
    @Test
    public void ReadTable()  {

        ProductRepository repository = new ProductRepository();
        Collection<Person> personList = repository.readAllPersons();



        System.out.println(personList);
//       System.out.println(repository.readAllPersons());

    }

    /**
     * Delete table.
     * @throws SQLException
     */
    @Test
    public void deleteTable() throws SQLException {

        String nameTable = "DROP TABLE person";

            ProductRepository repository = new ProductRepository();
            repository.deleteTable(nameTable);
    }


}

