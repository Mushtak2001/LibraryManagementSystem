/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package librarymanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AddBookPageController implements Initializable {

    @FXML
    private RadioButton newbook;
    @FXML
    private ToggleGroup type;
    @FXML
    private RadioButton usedbook;
    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private TextField athour;
    @FXML
    private CheckBox rent;
    @FXML
    private DatePicker date;
    @FXML
    private Button save;
    @FXML
    private Button cancel;
    private String booktype;
    private boolean rental;
    @FXML
    private TextField pages;
    @FXML
    private TextField price;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        booktype = "new";
        try {
            id.setText(creatid() + "");
        } catch (FileNotFoundException e) {
            id.setText("1");
        }
    }

    @FXML
    private void typebook(ActionEvent event) {
        if (type.equals(newbook))
            booktype = "new";
        else
            booktype = "used";
    }

    @FXML
    private void rentalmethod(ActionEvent event) {
        if (rent.isSelected())
            rental = true;
    }

    @FXML
    private void savebook(ActionEvent event) throws FileNotFoundException {
        if (checkdata()) {
            try {

                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to save this book?", "Save Book", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    String bookname = name.getText();
                    int bookid = Integer.parseInt(id.getText());
                    String bookathour = athour.getText();
                    int bookpages = Integer.parseInt(pages.getText());
                    double bookprice = Double.parseDouble(price.getText());
                    String bookdate = date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    Book book = new Book(bookid, bookname, bookpages, bookprice, bookathour, bookdate, rental, booktype);
                    book.savebook();
                    JOptionPane.showMessageDialog(null, "Book Saved Successfully");
                    Stage stage = (Stage) save.getScene().getWindow();
                    stage.close();
                    Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    JOptionPane.showMessageDialog(null, "Book Not Saved");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error in saving book");

            }
        } else {
            JOptionPane.showMessageDialog(null, "please enter all data");
        }


    }

    @FXML
    private void cancelbook(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    // method to creat id automaticly
    public int creatid() throws FileNotFoundException {
        File file = new File("books.txt");
        Scanner scanner = new Scanner(file);
        int id = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(",");
            id = Integer.parseInt(data[0]);
        }
        return id + 1;
    }

    //check if all data is entered
    public boolean checkdata() {
        //check if all data is entered
        if (name.getText().isEmpty() || athour.getText().isEmpty() || pages.getText().isEmpty() || price.getText().isEmpty() || date.getValue() == null) {
            return false;
        } else {
            return true;
        }
    }

}
