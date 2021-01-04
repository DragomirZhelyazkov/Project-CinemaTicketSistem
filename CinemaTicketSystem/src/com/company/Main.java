package com.company;

import controllers.NavigationController;
import io.ConsoleDevice;


public class Main {

    public static void main(String[] args) {
        NavigationController.getInstance(new ConsoleDevice()).startLogin();
    }
}


