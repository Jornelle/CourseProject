package Utils;

import Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.PrimitiveIterator;

public class DataBases {
    Connection connection;
    public DataBases(){
        String url = "jdbc:sqlserver://LAPTOP-0NF6KCRU;databaseName=Library";
        String name="sa";
        String password="123";
        try {
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("Connect 2 SQLserver");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("AddPromiser");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addUser(User user){
        String sql = "InsertUser " + user.getPassport() + ", '" + user.getName() + "', '" + user.getSecondName() +
                "', '" + user.getLastName() + "', '" + user.getAddress() + "', '" + user.getPhone() +"'";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ObservableList<User> getUsers() throws Exception {
        ObservableList<User> users = FXCollections.observableArrayList();
        String sql = "SelectUser";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User(resultSet.getInt("Pasport"),
                        resultSet.getString("Name"),
                        resultSet.getString("SecondName"),
                        resultSet.getString("Surname"),
                        resultSet.getString("Adress"),
                        resultSet.getString("Phone"));
                users.add(user);
            }
        return users;
    }

    public void deleteUser(int id){
        String sql = "deleteUser " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updUser(User user) throws SQLException {
        String sql = "UpdateUser " + user.getPassport() + ", '" + user.getName() + "', '" + user.getSecondName() +
                "', '" + user.getLastName() + "', '" + user.getAddress() + "', '" + user.getPhone() +"'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
    }

    public void addBook(Book book) throws SQLException {
        String sql = "InsertBook '" + book.getAuthor() + "', '" + book.getTitle() + "', " +
                book.getYear() + ", " + book.getCount();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public ObservableList<Book> getBook(){
        String sql = "SelectBook";
        ObservableList<Book> books = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                try {
                    Book book = new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("Author"),
                            resultSet.getString("Title"),
                            resultSet.getInt("Year"),
                            resultSet.getInt("Count")
                    );
                    books.add(book);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    public void delBook(int id){
        String sql = "deleteBook " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updBook(Book book) throws SQLException {
        String sql = "UpdateBook " + book.getId() +", '" + book.getAuthor() + "', '" + book.getTitle() + "', " +
                book.getYear() + ", " + book.getCount();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public ObservableList<Integer> getUsersPass(){
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        String sql = "getUsersId";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ids.add(resultSet.getInt("Pasport"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ids;
    }

    public ObservableList<Integer> getBookPass(){
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        String sql = "getBooksId";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ids.add(resultSet.getInt("id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ids;
    }

    public ObservableList<Reader> getReaders(){
        ObservableList<Reader> readers = FXCollections.observableArrayList();
        String sql = "SelectReader";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Reader reader = new Reader();
                reader.setBookId(resultSet.getInt("Book_Id"));
                reader.setUserPass(resultSet.getInt("Pasport"));
                reader.setLease(resultSet.getDate("Lease").toLocalDate());
                reader.setOverdue(resultSet.getBoolean("Overdue"));
                reader.setReceiving(resultSet.getDate("Receiving").toLocalDate());
                reader.setId(resultSet.getInt("id"));
                readers.add(reader);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return readers;
    }

    public void addReader(Reader reader) throws SQLException {
        String sql = "InsertReader " + reader.getUserPass() + ", " + reader.getBookId();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
    }

    public int delReader(int id, String condition){
        int ret = 0;
        try {
            CallableStatement call = connection.prepareCall("{ call deleteReaders(?, ?, ?) }");
            call.setInt(1, id);
            call.setString(2, condition);
            call.registerOutParameter(3, java.sql.Types.INTEGER);
            call.execute();
            call.getMoreResults();
            ret = call.getInt(3);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ret;
    }

    public void delPromiser(int id){
        String sql = "deletePromiser " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ObservableList<Promiser> getPromisers(){
        String sql = "selectPromiser";
        ObservableList<Promiser> promisers = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Promiser promiser = new Promiser();
                promiser.setTitle(resultSet.getString("title"));
                promiser.setAuthor(resultSet.getString("author"));
                promiser.setId(resultSet.getInt("id"));
                promiser.setUser_Pasport(resultSet.getInt("User_Pasport"));
                promisers.add(promiser);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return promisers;
    }

}
