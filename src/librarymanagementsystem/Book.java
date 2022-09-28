/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem;

import java.io.*;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author HP
 */
public class Book {
     private int id;
     private String name;
     private int pages;
     private double price;
     private String auther;
     private String addedDate;
     private boolean rental;
     private String type;

    public Book() {
    }

    public Book(int id, String name, int pages, double price, String auther, String addedDate, boolean rental, String type) {
        this.id = id;
        this.name = name;
        this.pages = pages;
        this.price = price;
        this.auther = auther;
        this.addedDate = addedDate;
        this.rental = rental;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public boolean isRental() {
        return rental;
    }

    public void setRental(boolean rental) {
        this.rental = rental;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void savebook() throws IOException {

        BufferedWriter bw=new BufferedWriter(new FileWriter("books.txt",true));
        bw.write(id+","+name+","+pages+","+price+","+auther+","+addedDate+","+rental+","+type+"\n");
        bw.close();

    }


    @Override
    public String toString() {
        return "book info\n"+"id=" + id +"\n" +
                "name=" + name +"\n" +
                "pages=" + pages +"\n" +
                "price=" + price +"\n" +
                "auther=" + auther +"\n" +
                "added Date=" + addedDate +"\n" +
                "rental=" + rental +"\n" +
                "type=" + type +"\n" ;
    }
}
