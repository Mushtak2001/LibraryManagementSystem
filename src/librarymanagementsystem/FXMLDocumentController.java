/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package librarymanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private MenuBar menuBar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addMethod(ActionEvent event) throws IOException {
        Stage stage=(Stage) menuBar.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("AddBookPage.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("add book");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void exetMethod(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void deleteMethod(ActionEvent event) throws IOException {
        Stage stage=(Stage) menuBar.getScene().getWindow();
        stage.hide();
        Parent root = FXMLLoader.load(getClass().getResource("DeleteBook.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("edit book");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void aboutMethod(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AboutFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("about");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void viewMethod(ActionEvent event) throws IOException {
        Stage stage=(Stage) menuBar.getScene().getWindow();
        stage.hide();
        Parent root = FXMLLoader.load(getClass().getResource("ViewBook.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("view book");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void SaleMethod(ActionEvent event) throws IOException {
        Stage stage=(Stage) menuBar.getScene().getWindow();
        stage.hide();
        Parent root = FXMLLoader.load(getClass().getResource("SaleBook.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("sale book");
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void editMethod(ActionEvent event) throws IOException {
        Stage stage=(Stage) menuBar.getScene().getWindow();
        stage.hide();
        Parent root = FXMLLoader.load(getClass().getResource("EditBook.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("edit book");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void StatisticsMehtod(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Sales.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("statistics");
        stage.setScene(scene);
        stage.show();

    }
    
}
