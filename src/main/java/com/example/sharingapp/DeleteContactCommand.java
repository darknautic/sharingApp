package com.example.sharingapp;
import android.content.Context;

/**
 * Created by esajaus on 3/18/2018.
 */

public class DeleteContactCommand extends Command  {

    private ContactList contact_list;
    private Contact contact;
    private Context context;

    public DeleteContactCommand(ContactList contact_list, Contact contact, Context context){
        this.contact_list = contact_list;
        this.contact = contact;
        this.context = context;

    }

    public void execute() {

        contact_list.deleteContact(contact);
        setIsExecuted(contact_list.saveContacts(context));

    }


}
