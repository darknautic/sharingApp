package com.example.sharingapp;
import java.util.ArrayList;
/**
 * Created by esajaus on 3/24/2018.
 */

public class Observable {

    private ArrayList<Observer> observers = null;

    public Observable(){
        observers =  new ArrayList<Observer>();
    }

    public  void notifyObservers(){
        for (Observer o: observers){
            o.update();
        }
    }

    public  void addObserver(Observer o){
        observers.add(o);
    }

    public  void removeObserver(Observer o ){
        if (observers.contains(o)){
            observers.remove(o);
        }
    }
}
