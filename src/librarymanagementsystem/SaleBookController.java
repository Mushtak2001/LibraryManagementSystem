/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package librarymanagementsystem;

import java.io.*;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SaleBookController implements Initializable {

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
    private Button salept;
    @FXML
    private Button cancel;
    private boolean isSelect = false;

    /**
     * Initializes the controller class.
     */
    ObservableList<Book> list = FXCollections.observableArrayList(
            getBooks()

    );
    @FXML
    private RadioButton change;
    @FXML
    private TextField newPrice;
    @FXML
    private Label pricenme;

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
                JOptionPane.showMessageDialog(null, "File not found");
            }
        }
        return books;
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        pages.setCellValueFactory(new PropertyValueFactory<Book, Integer>("pages"));
        price.setCellValueFactory(new PropertyValueFactory<Book, Double>("price"));
        auther.setCellValueFactory(new PropertyValueFactory<Book, String>("auther"));
        addedDate.setCellValueFactory(new PropertyValueFactory<Book, String>("addedDate"));
        rental.setCellValueFactory(new PropertyValueFactory<Book, Boolean>("rental"));
        type.setCellValueFactory(new PropertyValueFactory<Book, String>("type"));
        table.setItems(list);

    }


    @FXML
    private void cancelMethod(ActionEvent event) throws IOException {
        //hide the current window
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void getselected(MouseEvent event) {
        isSelect = true;

    }

    public void saleMethod() {
        // update the book to the file
        if (isSelect) {
            // get selected id from table
            int id = table.getSelectionModel().getSelectedItem().getId();
            // get the book from the file
            ArrayList<Book> books = getBooks();
            // update the book
            File file = new File("books.txt");
            if (file.exists()) {
                try {
                    Scanner scanner = new Scanner(file);
                    PrintWriter writer = new PrintWriter(file);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] data = line.split(",");
                        Book book = new Book(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]), data[4], data[5], Boolean.parseBoolean(data[6]), data[7]);
                        books.add(book);
                    }

                    scanner.close();
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to sale this book?", "Sale Book", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        for (int i = 0; i < books.size(); i++) {
                            if (books.get(i).getId() == id) {
                                double price1 = books.get(i).getPrice();
                                amountMethod(price1);
                                books.remove(i);
                            } else {
                                writer.println(books.get(i).getId() + "," + books.get(i).getName() + "," + books.get(i).getPages() + "," + books.get(i).getPrice() + "," + books.get(i).getAuther() + "," + books.get(i).getAddedDate() + "," + books.get(i).isRental() + "," + books.get(i).getType());
                            }
                        }
                        writer.close();
                        JOptionPane.showMessageDialog(null, "Book updated successfully");
                        Stage stage = (Stage) salept.getScene().getWindow();
                        stage.close();
                        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } else if (result == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "Book not Sale");
                    }

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error in sealing book");
                }
            }

        }
    }

    @FXML
    private void selecing(ActionEvent event) {
        if (change.isSelected()) {
            newPrice.setVisible(true);
            pricenme.setVisible(true);
        } else {
            newPrice.setVisible(false);
            pricenme.setVisible(false);
        }
    }

    @FXML
    private void saleMethod(ActionEvent event) {
        saleMethod();
    }

    public void amountMethod(double amount) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("sale.txt", true));
        if (!change.isSelected()) {
            writer.write(amount + "");
            writer.newLine();
        } else {
            // write the sale from the new price to the file
            writer.write(newPrice.getText());
            writer.newLine();
        }
        writer.close();
    }

}
