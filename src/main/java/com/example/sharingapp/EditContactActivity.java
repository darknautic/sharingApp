package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

/**
 * Editing a pre-existing contact consists of deleting the old contact and adding a new contact with the old
 * contact's id.
 * Note: You will not be able contacts which are "active" borrowers
 */
public class EditContactActivity extends AppCompatActivity implements  Observer{

    private ContactList contact_list = new ContactList();
    private ContactListController contact_list_controller = new ContactListController(contact_list);
    private Contact contact;
    //private ContactController contact_controller = new ContactController(contact);
    private ContactController contact_controller;
    private EditText email;
    private EditText username;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        context = getApplicationContext();
        //contact_list.loadContacts(context);

        contact_list_controller.addObserver(this);
        contact_list_controller.loadContacts(context);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);

        contact = contact_list.getContact(pos);
        contact_controller = new ContactController(contact);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        username.setText(contact_controller.getUsername());
        email.setText(contact_controller.getEmail());


    }

    public void saveContact(View view) {

        String email_str = email.getText().toString();

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return;
        }

        if (!email_str.contains("@")){
            email.setError("Must be an email address!");
            return;
        }

        String username_str = username.getText().toString();
        String id = contact_controller.getId(); // Reuse the contact id

        // Check that username is unique AND username is changed (Note: if username was not changed
        // then this should be fine, because it was already unique.)
        if (!contact_list_controller.isUsernameAvailable(username_str) && !(contact_controller.getUsername().equals(username_str))) {
            username.setError("Username already taken!");
            return;
        }

        Contact updated_contact = new Contact(username_str, email_str, id);

        //contact_list.deleteContact(contact);
        //contact_list.addContact(updated_contact);
        //contact_list.saveContacts(context);

        //EditContactCommand edit_contact_command = new EditContactCommand(contact_list,contact,updated_contact,context);
        //edit_contact_command.execute();

        //boolean success = edit_contact_command.isExecuted();
        boolean success = contact_list_controller.editContact(contact,updated_contact,context);
        if(!success){
            return;
        }
        else{
            System.out.println("Error Editing contact , not successful");
        }

        // End EditContactActivity
        finish();
    }

    public void deleteContact(View view) {


        //contact_list.deleteContact(contact);
        //contact_list.saveContacts(context);

        //DeleteContactCommand delete_contact_command = new DeleteContactCommand(contact_list,contact,context);
        //delete_contact_command.execute();

        //boolean success = delete_contact_command.isExecuted();
        boolean success = contact_list_controller.deleteContact(contact,context);
        if(!success){
            return;
        }
        contact_list_controller.removeObserver(this);
        // End EditContactActivity
        finish();
    }


    public void update(){

    }



}
