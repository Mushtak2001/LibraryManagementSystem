/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package librarymanagementsystem;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SalesController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private Label avaible;
    @FXML
    private Label sold;
    @FXML
    private Label amount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        amount.setText(totalAmount()+" SR");
        sold.setText(totalSold()+" books sold");
        avaible.setText(totalAvaible()+" books avaible");

    }    

    @FXML
    private void closeMethod(ActionEvent event) throws IOException {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private double totalAmount(){
        // get the total amount of sold books from the file
        File file=new File("sales.txt");
        double total=0;
        try {
            Scanner scanner=new Scanner(file);
            while(scanner.hasNext()){
                String line=scanner.nextLine();
                String[] data=line.split(" ");
                total+=Double.parseDouble(data[0]);
            }
        } catch (Exception e) {
            return 0;
        }
        return total;
    }
    private int totalSold(){
        File file=new File("sales.txt");
        int total=0;
        try {
            Scanner scanner=new Scanner(file);
            while(scanner.hasNext()){
                scanner.nextLine();
                total++;
            }
        } catch (Exception e) {
            return 0;
        }
        return total;
    }
    private int totalAvaible(){
        File file=new File("books.txt");
        int total=0;
        try {
            Scanner scanner=new Scanner(file);
            while(scanner.hasNext()){
                scanner.nextLine();
                total++;
            }
        } catch (Exception e) {
            return 0;
        }
        return total;
    }
    
}
