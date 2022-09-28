/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package librarymanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.jar.JarEntry;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ViewBookController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Book> table;

    @FXML
    private TableColumn<Book, Integer> id;

    @FXML
    private TableColumn<Book, String> name;
    @FXML
    private TableColumn<Book, Integer> pages;
    @FXML
    private TableColumn<Book, Double> price;

    @FXML
    private TableColumn<Book, String> auther;

    @FXML
    private TableColumn<Book, String> addedDate;

    @FXML
    private TableColumn<Book, Boolean> rental;

    @FXML
    private TableColumn<Book, String> type;
    @FXML
    void closeMetho(ActionEvent event) throws IOException {
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    ObservableList<Book> list;

    {
        try {
            list = FXCollections.observableArrayList(
                    getBooks()

            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public ArrayList<Book> getBooks() throws IOException {
        ArrayList<Book> books = new ArrayList<>();
        File file = new File("books.txt");
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] data = line.split(",");
                    Book book = new Book(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]), data[4], data[5], Boolean.parseBoolean(data[6]), data[7]);
                    books.add(book);
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "File not found");
                Stage stage = (Stage) table.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        return books;
    }
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setCellValueFactory(new PropertyValueFactory<Book,Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        pages.setCellValueFactory(new PropertyValueFactory<Book,Integer>("pages"));
        price.setCellValueFactory(new PropertyValueFactory<Book,Double>("price"));
        auther.setCellValueFactory(new PropertyValueFactory<Book,String>("auther"));
        addedDate.setCellValueFactory(new PropertyValueFactory<Book,String>("addedDate"));
        rental.setCellValueFactory(new PropertyValueFactory<Book,Boolean>("rental"));
        type.setCellValueFactory(new PropertyValueFactory<Book,String>("type"));
        table.setItems(list);
    }
}



