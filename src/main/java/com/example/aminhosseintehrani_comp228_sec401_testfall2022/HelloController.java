package com.example.aminhosseintehrani_comp228_sec401_testfall2022;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TableView bookTable;
    CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
    @FXML
    private TableColumn s_bookID_column;
    @FXML
    private TableColumn s_title_column;

    @FXML
    private TableColumn s_category_column;

    @FXML
    private TableColumn s_p_year_column;

    @FXML
    private TableColumn s_author_column;
    @FXML
    private TextField bookIDTextField;

    @FXML
    private TextField titleTextField;


    @FXML
    private TextField categoryTextField;

    @FXML
    private TextField p_yearTextField;

    @FXML
    private TextField authorTextField;

    @FXML
    private TextField categoryFilterTextField;

    @FXML
    private TextField  yearFilterTextField;


    @FXML
    private ComboBox conditionComboBox;

    private ResultSet set;

    ObservableList<Book> books = FXCollections.observableArrayList();

    public HelloController() throws SQLException {
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void OnSubmitBookInfo(ActionEvent actionEvent) throws SQLException {

        DBUtil.insertBookData(Integer.parseInt(bookIDTextField.getText()),
                titleTextField.getText(),
                authorTextField.getText(),
               categoryTextField.getText(),
               Integer.parseInt(p_yearTextField.getText()));


    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeCombo();
   StartupTableView();

    }


    public void StartupTableView(){


        ResultSetMetaData rsmd = null;

        try {
            set = DBUtil.query("SELECT * FROM Book" );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            crs.populate(set);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }





        int b = 0;


        try {
            set.beforeFirst();
            while(set.next()) {
                Book book = new Book();
                book.setBookID(Integer.parseInt(set.getString("bookID")));
                book.setTitle(set.getString("title"));
                book.setAuthor(set.getString("author"));
                book.setCategory(set.getString("category"));
                book.setP_year(Integer.parseInt(set.getString("p_year")));
                books.add(book);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        s_bookID_column.setCellValueFactory(new PropertyValueFactory("bookID"));
        s_title_column.setCellValueFactory(new PropertyValueFactory("title"));

        s_category_column.setCellValueFactory(new PropertyValueFactory("category"));
        s_p_year_column.setCellValueFactory(new PropertyValueFactory("p_year"));

        s_author_column.setCellValueFactory(new PropertyValueFactory("author"));

        //clear table before adding new records
        bookTable.getItems().clear();



        // add data to the table
        bookTable.getItems().addAll(books);

        // sort the table by student id
        s_bookID_column.setSortType(TableColumn.SortType.ASCENDING);
        bookTable.getSortOrder().add(s_bookID_column);
        bookTable.sort();

    }

    public void filterViewTable(String category,String yearFilter, String yearFilterSymbol){
books = FXCollections.observableArrayList();

boolean emptyYearFilterField = true;
boolean emptyCategoryFilterField = true;

String symbol = null;

if(category == ""){
    emptyCategoryFilterField = true;
}
else{
    emptyCategoryFilterField = false;
}
System.out.println(emptyCategoryFilterField +  "emtpty cat");
//if(yearFilter.equalsIgnoreCase("")){
//    yearFilter = "0";
//}
System.out.println(yearFilter + "year filter");
        if(yearFilter == "" ){
            emptyYearFilterField = true;
        }
        else{
          emptyYearFilterField = false;
        }








        ResultSetMetaData rsmd = null;

        try {
            set = DBUtil.query("SELECT * FROM Book" );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            crs.populate(set);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }





        int b = 0;


        try {
            set.beforeFirst();
            while(set.next()) {
                Book book = new Book();
                book.setBookID(Integer.parseInt(set.getString("bookID")));
                book.setTitle(set.getString("title"));
                book.setAuthor(set.getString("author"));
                book.setCategory(set.getString("category"));
                book.setP_year(Integer.parseInt(set.getString("p_year")));
                books.add(book);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        s_bookID_column.setCellValueFactory(new PropertyValueFactory("bookID"));
        s_title_column.setCellValueFactory(new PropertyValueFactory("title"));

        s_category_column.setCellValueFactory(new PropertyValueFactory("category"));
        s_p_year_column.setCellValueFactory(new PropertyValueFactory("p_year"));

        s_author_column.setCellValueFactory(new PropertyValueFactory("author"));

        //clear table before adding new records
        bookTable.getItems().clear();







        // add data to the table
//        for(int i = 0; i<= books.size() -1; i+=1){
//
//
//            int count = 0;
//            if(books.get(i).getCategory().equalsIgnoreCase(category) && category != "" && yearFilter != 0){
//
//                bookTable.getItems().add(books.get(i));
//                count +=1;
//                System.out.println(count);
//
//            }
//        }


                for(int i = 0; i<= books.size() -1; i+=1){


if(emptyCategoryFilterField == false && emptyYearFilterField == true){
    if(books.get(i).getCategory().equalsIgnoreCase(category)){
        bookTable.getItems().add(books.get(i));

    }



}

                    if(emptyCategoryFilterField == true && emptyYearFilterField == false && yearFilterSymbol.equalsIgnoreCase(">")){
                        if(books.get(i).getP_year() > Integer.parseInt(yearFilter)){
                            bookTable.getItems().add(books.get(i));

                        }



                    }

                    if(emptyCategoryFilterField == true && emptyYearFilterField == false && yearFilterSymbol.equalsIgnoreCase("<")) {
                        if (books.get(i).getP_year() < Integer.parseInt(yearFilter)) {
                            bookTable.getItems().add(books.get(i));

                        }
                    }

                    System.out.println(emptyYearFilterField);
                        if(emptyCategoryFilterField == true && emptyYearFilterField == false && yearFilterSymbol.equalsIgnoreCase("=")){
                            if(books.get(i).getP_year() == Integer.parseInt(yearFilter)){
                                bookTable.getItems().add(books.get(i));

                            }



                    }

                    if(emptyCategoryFilterField == false && emptyYearFilterField == false && yearFilterSymbol.equalsIgnoreCase("<")) {
                        if (books.get(i).getCategory().equalsIgnoreCase(category) && books.get(i).getP_year() < Integer.parseInt(yearFilter)) {
                            bookTable.getItems().add(books.get(i));

                        }
                    }

                        if (emptyCategoryFilterField == false && emptyYearFilterField == false && yearFilterSymbol.equalsIgnoreCase(">")) {
                            if (books.get(i).getCategory().equalsIgnoreCase(category) && books.get(i).getP_year() > Integer.parseInt(yearFilter)) {
                                bookTable.getItems().add(books.get(i));

                            }
                        }

                            if (emptyCategoryFilterField == false && emptyYearFilterField == false && yearFilterSymbol.equalsIgnoreCase("=")) {
                                if (books.get(i).getCategory().equalsIgnoreCase(category) && books.get(i).getP_year() == Integer.parseInt(yearFilter)) {
                                    bookTable.getItems().add(books.get(i));

                                }


                            }


                        }






      //  bookTable.getItems().addAll(books);

        // sort the table by student id
        s_bookID_column.setSortType(TableColumn.SortType.ASCENDING);
        bookTable.getSortOrder().add(s_bookID_column);
        bookTable.sort();

    }

    public void makeCombo(){
        String indicators[] = { ">", "=", "<"};
        conditionComboBox.getItems().removeAll(conditionComboBox.getItems());
        conditionComboBox.getItems().addAll(indicators);
        conditionComboBox.getSelectionModel().select(">");
    }


    public void OnFilterTable(ActionEvent actionEvent) {
System.out.println(categoryFilterTextField.getText() + "good");
        filterViewTable( categoryFilterTextField.getText(), yearFilterTextField.getText(),conditionComboBox.getValue().toString());
    }
}
