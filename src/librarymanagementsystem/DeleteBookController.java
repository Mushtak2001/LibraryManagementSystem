/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package librarymanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DeleteBookController implements Initializable {

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
    private TextField searchID;
    @FXML
    private Button delete;
    @FXML
    private Button cancel;

    @FXML
    void cancelMthod(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    ObservableList<Book> list = FXCollections.observableArrayList(
            getBooks()

    );
    public ArrayList<Book> getBooks() {
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
                JOptionPane.showMessageDialog(null, "Error");
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

    @FXML
    private void deleteMethod(ActionEvent event) {
        // delete book from file

        ArrayList<Book> listbooks = getBooks();
        File file = new File("books.txt");
        try {
            int id1 = Integer.parseInt(searchID.getText());
            Scanner scanner = new Scanner(file);
            PrintWriter writer = new PrintWriter(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                listbooks.add(new Book(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]), data[4], data[5], Boolean.parseBoolean(data[6]), data[7]));
            }
            int index = -1;
            for (int i = 0; i < listbooks.size(); i++) {
                if (listbooks.get(i).getId() == id1) {
                    index = i;
                    break;
                }
            }
            scanner.close();
            if (index!=-1){
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this book?", "Delete Book", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    listbooks.remove(index);
                    for (int i = 0; i < listbooks.size(); i++) {
                        writer.println(listbooks.get(i).getId() + "," + listbooks.get(i).getName() + "," + listbooks.get(i).getPages() + "," + listbooks.get(i).getPrice() + "," + listbooks.get(i).getAuther() + "," + listbooks.get(i).getAddedDate() + "," + listbooks.get(i).isRental() + "," + listbooks.get(i).getType());
                    }
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Book deleted successfully");
                    Stage stage = (Stage) cancel.getScene().getWindow();
                    stage.close();
                    Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                }
                else {
                    JOptionPane.showMessageDialog(null, "Book not deleted");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Book not found");
            }
        } catch (NumberFormatException formatException){
            JOptionPane.showMessageDialog(null, "Please enter a valid ID");
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error");
        }

    }

}
