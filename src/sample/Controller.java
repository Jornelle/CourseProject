package sample;

import Classes.Book;
import Classes.Promiser;
import Classes.Reader;
import Classes.User;
import Utils.DataBases;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Tab tabUser;
    public TableView<User> tvUser;
    public TableColumn<User, Integer> tcPassport;
    public TableColumn<User, String> tcName;
    public TableColumn<User, String> tcSecondName;
    public TableColumn<User, String> tcSurName;
    public TableColumn<User, String> tcAdress;
    public TableColumn<User, String> tcPhone;
    public TextField txfPassport;
    public TextField txfName;
    public TextField txfSecondName;
    public TextField txfSurname;
    public TextField txfAddress;
    public TextField txfPhone;
    public GridPane gpButtons;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnDelete;
    public Label lblError;
    public Button btnClear;
    public TabPane tpItems;


    public TableView<Book> tvBook;
    public TableColumn<Book, Integer> tcNumber;
    public TableColumn<Book, String> tcNameBook;
    public TableColumn<Book, String> tcAuthor;
    public TableColumn<Book, Integer> tcYear;
    public TableColumn<Book, Integer> tcCount;
    public TextField txfNameBook;
    public TextField txfAuthor;
    public TextField txfYear;
    public TextField txfCount;
    public Tab tabBook;
    public Label lblBookId;

    public TableView<Reader> tvReaders;
    public TableColumn<Reader, Integer> tcPassportReader;
    public TableColumn<Reader, LocalDate> tcReceiving;
    public TableColumn<Reader, LocalDate> tcLease;
    public TableColumn<Reader, Boolean> tcOverdue;
    public ComboBox<Integer> cmbPassport = new ComboBox<>();
    public ComboBox<Integer> cmbID = new ComboBox<>();
    public TableColumn<Reader, Integer> tcIdBook;
    public Label lblReaderId;
    public ComboBox<String> cmbReaderCondition = new ComboBox<>();
    public TableColumn<Reader, Integer> tcID;
    
    public TableView<Promiser> tvPromiser;
    public TableColumn<Promiser, Integer> tcPassportPromiser;
    public TableColumn<Promiser, String> tcTitle;
    public TableColumn<Promiser, String> tcAuthorPromiser;
    public TableColumn<Promiser, Integer> tcIdPromiser;

    @FXML
    Label lblPromiseId;
    @FXML
    Label lblCondition;
    @FXML
    Button btnSearch;
    @FXML
    TextField txfBookTitle;
    @FXML
    TextArea txaBookInfo;


    private DataBases dataBases;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBases = new DataBases();
        initTable();
        initCombos();
        TableView.TableViewSelectionModel<User> selectionUser = tvUser.getSelectionModel();
        selectionUser.selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observableValue, User user, User t1) {
                if(t1 != null){
                    txfPassport.setText(String.valueOf(t1.getPassport()));
                    txfName.setText(t1.getName());
                    txfSecondName.setText(t1.getSecondName());
                    txfSurname.setText(t1.getLastName());
                    txfAddress.setText(t1.getAddress());
                    txfPhone.setText(t1.getPhone());
                    btnDelete.setDisable(false);
                    btnUpdate.setDisable(false);
                }
            }
        });
        TableView.TableViewSelectionModel<Book> selectionBook = tvBook.getSelectionModel();
        selectionBook.selectedItemProperty().addListener(new ChangeListener<Book>() {
            @Override
            public void changed(ObservableValue<? extends Book> observableValue, Book book, Book t1) {
                if(t1 != null){
                    txfAuthor.setText(t1.getAuthor());
                    txfNameBook.setText(t1.getTitle());
                    txfCount.setText(String.valueOf(t1.getCount()));
                    txfYear.setText(String.valueOf(t1.getYear()));
                    lblBookId.setText(String.valueOf(t1.getId()));
                    btnDelete.setDisable(false);
                    btnUpdate.setDisable(false);
                }
            }
        });
        TableView.TableViewSelectionModel<Reader> selectionReader = tvReaders.getSelectionModel();
        selectionReader.selectedItemProperty().addListener(new ChangeListener<Reader>() {
            @Override
            public void changed(ObservableValue<? extends Reader> observableValue, Reader reader, Reader t1) {
                if(t1 != null){
                    lblReaderId.setText(String.valueOf(t1.getId()));
                    btnDelete.setDisable(false);
                    cmbReaderCondition.setVisible(true);
                    lblCondition.setVisible(true);
                }
            }
        });

        TableView.TableViewSelectionModel<Promiser> selectionPromiser = tvPromiser.getSelectionModel();
        selectionPromiser.selectedItemProperty().addListener(new ChangeListener<Promiser>() {
            @Override
            public void changed(ObservableValue<? extends Promiser> observableValue, Promiser promiser, Promiser t1) {
                if(t1 != null){
                    lblPromiseId.setText(String.valueOf(t1.getId()));
                    btnDelete.setDisable(false);
                }
            }
        });
        cmbID.setOnAction(event -> {
            try {
                txaBookInfo.setText(String.valueOf(dataBases.searchBook(Integer.parseInt(String.valueOf(cmbID.getValue())))));
            }
            catch (NullPointerException ex){

            }
        });

        tpItems.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
                switch (tpItems.getSelectionModel().getSelectedItem().getId()){
                    case "tabUser", "tabBook" -> {
                        btnAdd.setText("Добавить");
                        btnDelete.setText("Удалить");
                        btnUpdate.setText("Изменить");
                        if(!lblBookId.getText().equals("")){
                            btnUpdate.setDisable(false);
                            btnDelete.setDisable(false);
                        }
                        if(!txfPassport.getText().equals("")){
                            btnUpdate.setDisable(false);
                            btnDelete.setDisable(false);
                        }
                    }
                    case "tabReader" -> {
                        btnAdd.setText("Добавить");
                        btnDelete.setText("Вернуть");
                        if(!lblReaderId.getText().equals("")){
                            btnUpdate.setDisable(false);
                        }
                    }
                }
            }
        });
    }

    public void btnAddClick(ActionEvent actionEvent) {
        lblError.setText("");
        switch (tpItems.getSelectionModel().getSelectedItem().getId()){
            case "tabUser" -> {
                int passport;
                try{
                    passport = Integer.parseInt(txfPassport.getText());
                }
                catch (Exception ex){
                    lblError.setText("Введите номер паспорта");
                    txfPassport.setText("");
                    return;
                }
                try{
                    User user = new User(
                            passport, txfName.getText(),
                            txfSecondName.getText(),
                            txfSurname.getText(),
                            txfAddress.getText(),
                            txfPhone.getText()
                    );
                    dataBases.addUser(user);
                    initTable();
                    clearUser();
                }
                catch (Exception ex){
                    lblError.setText(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            case "tabBook" -> {
                int year;
                try{
                    year = Integer.parseInt(txfYear.getText());
                }
                catch (Exception ex){
                    lblError.setText("Введите год");
                    txfYear.setText("");
                    return;
                }
                int count;
                try{
                    count = Integer.parseInt(txfCount.getText());
                    if(count < 1){
                        throw  new Exception("Не верное кол-во");
                    }
                }
                catch (Exception ex){
                    lblError.setText("Введите количество");
                    txfCount.setText("");
                    return;
                }
                try {
                    Book book = new Book(0,
                            txfAuthor.getText(),
                            txfNameBook.getText(),
                            year,
                            count
                            );
                    dataBases.addBook(book);
                    initTable();
                    clearBook();
                } catch (Exception e) {
                    lblError.setText(e.getMessage());
                    e.printStackTrace();
                }

            }
            case "tabReader" -> {
                Reader reader = new Reader();
                reader.setBookId(cmbID.getValue());
                try {
                    reader.setUserPass(cmbPassport.getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                try {
                    dataBases.addReader(reader);
                } catch (SQLException e) {
                    lblError.setText(e.getMessage());
                }
                initTable();
                initCombos();
            }
        }
    }

    public void btnUpdateClick(ActionEvent actionEvent) {
        lblError.setText("");
        switch (tpItems.getSelectionModel().getSelectedItem().getId()){
            case "tabUser" -> {
                try{
                    int passport;
                    try{
                        passport = Integer.parseInt(txfPassport.getText());
                    }
                    catch (Exception ex){
                        lblError.setText("Введите номер паспорта");
                        txfPassport.setText("");
                        return;
                    }
                    User user = new User(
                            passport, txfName.getText(),
                            txfSecondName.getText(),
                            txfSurname.getText(),
                            txfAddress.getText(),
                            txfPhone.getText()
                    );
                    dataBases.updUser(user);
                    btnDelete.setDisable(true);
                    btnUpdate.setDisable(true);
                }
                catch (SQLException ex){
                    lblError.setText(ex.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                initTable();
            }
            case "tabBook" -> {
                int year;
                try{
                    year = Integer.parseInt(txfYear.getText());
                }
                catch (Exception ex){
                    lblError.setText("Введите год");
                    txfYear.setText("");
                    return;
                }
                int count;
                try{
                    count = Integer.parseInt(txfCount.getText());
                }
                catch (Exception ex){
                    lblError.setText("Введите количество");
                    txfCount.setText("");
                    return;
                }
                try {
                    Book book = new Book(Integer.parseInt(lblBookId.getText()),
                            txfAuthor.getText(),
                            txfNameBook.getText(),
                            year,
                            count
                    );
                    dataBases.updBook(book);
                    initTable();
                    clearBook();
                    btnDelete.setDisable(true);
                    btnUpdate.setDisable(true);
                } catch (Exception e) {
                    lblError.setText(e.getMessage());
                    e.printStackTrace();
                }

            }
        }
    }

    public void btnDeleteClick(ActionEvent actionEvent) {
        lblError.setText("");
        switch (tpItems.getSelectionModel().getSelectedItem().getId()){
            case "tabUser" -> {
                int passport;
                try{
                    passport = Integer.parseInt(txfPassport.getText());
                }
                catch (Exception ex){
                    lblError.setText("Введите номер паспорта");
                    txfPassport.setText("");
                    return;
                }

                try {
                    dataBases.deleteUser(passport);
                } catch (SQLException throwables) {
                    lblError.setText(throwables.getMessage());
                }
            }
            case "tabBook" -> {
                dataBases.delBook(Integer.parseInt(lblBookId.getText()));
            }
            case "tabReader" -> {
                int sum = dataBases.delReader(Integer.parseInt(lblReaderId.getText()), cmbReaderCondition.getValue());
                if(sum != 0) {
                    lblError.setText("Читатель должен " + Math.abs(sum) + " руб.");
                }
            }
            case "tabPromiser" -> {
                dataBases.delPromiser(Integer.parseInt(lblPromiseId.getText()));
            }

        }
        initTable();
        btnDelete.setDisable(true);
        cmbReaderCondition.setVisible(false);
        lblCondition.setVisible(false);
    }

    private void initTable(){
        //Таблица пользователей
        tcPassport.setCellValueFactory(new PropertyValueFactory<>("passport"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcSecondName.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        tcSurName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcAdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        try {
            tvUser.setItems(dataBases.getUsers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Таблица книг
        tcNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNameBook.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tcYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        tcCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        tvBook.setItems(dataBases.getBook());

        //Таблица читателей
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcIdBook.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        tcPassportReader.setCellValueFactory(new PropertyValueFactory<>("userPass"));
        tcReceiving.setCellValueFactory(new PropertyValueFactory<>("receiving"));
        tcLease.setCellValueFactory(new PropertyValueFactory<>("lease"));
        tcOverdue.setCellValueFactory(new PropertyValueFactory<>("overdue"));
        tvReaders.setItems(dataBases.getReaders());

        //Таблица должников
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcIdPromiser.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcPassportPromiser.setCellValueFactory(new PropertyValueFactory<>("User_Pasport"));
        tcAuthorPromiser.setCellValueFactory(new PropertyValueFactory<>("author"));
        tvPromiser.setItems(dataBases.getPromisers());
    }

    private void initCombos(){
        ObservableList<String> condition = FXCollections.observableArrayList();
        condition.add("Хорошее");
        condition.add("Плохое");
        cmbReaderCondition.setItems(condition);
        cmbPassport.setItems(dataBases.getUsersPass());
    }

    private void clearUser(){
        txfPassport.setText("");
        txfAddress.setText("");
        txfName.setText("");
        txfPhone.setText("");
        txfSurname.setText("");
        txfSecondName.setText("");
    }

    private void clearBook(){
        txfAuthor.setText("");
        txfNameBook.setText("");
        txfCount.setText("");
        txfYear.setText("");
        lblBookId.setText("");
        lblError.setText("");
    }

    public void btnClearClick(ActionEvent actionEvent) {
        switch (tpItems.getSelectionModel().getSelectedItem().getId()){
            case "tabUser" -> {
                clearUser();
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
            }
            case "tabBook" -> {
                clearBook();
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
            }
        }
    }

    @FXML
    public void btnSearchClick(ActionEvent actionEvent){
        lblError.setText("");
        var ids = dataBases.getBookPass(txfBookTitle.getText());
        if(ids.size() < 1){
            lblError.setText("Такой книги не найдено");
            return;
        }
        cmbID.setItems(ids);
        cmbID.setValue(ids.get(0));
        cmbID.setDisable(false);
        txaBookInfo.setText(String.valueOf(dataBases.searchBook(Integer.parseInt(String.valueOf(cmbID.getValue())))));
    }
}
