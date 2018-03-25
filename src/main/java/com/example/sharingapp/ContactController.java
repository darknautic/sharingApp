package com.example.sharingapp;
import android.graphics.Bitmap;


/**
 * Created by esajaus on 3/25/2018. *
 * ItemController is responsible for all communication between views and Item object
 */

public class ContactController {


    private Contact contact;
    public ContactController(Contact contact){
        this . contact = contact;
    }

    public String getId(){
        return contact.getId();
    }

    public void setId() {
        contact .setId();
    }

    public String getUsername(){
        return contact.getUsername();
    }

    public void setUsername(String username){
        contact.setUsername(username);
    }


    public String getEmail(){
        return  this.contact.getEmail();
    }

    public void setEmail(String email){
        contact.setEmail(email);
    }

    public Contact getContact() {
        return this . contact ;
    }

    public void addObserver(Observer observer) {
        contact.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        contact .removeObserver(observer);
    }


}
