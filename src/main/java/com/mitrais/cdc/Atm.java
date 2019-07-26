package com.mitrais.cdc;

import java.util.Scanner;


import com.mitrais.cdc.screen.WelcomeScreen;

/**
 * Hello world!
 *
 */
public class Atm 
{
    public static void main( String[] args )
    {
        WelcomeScreen welcomeScreen = new WelcomeScreen();

        while(true){
            welcomeScreen.start();
        }
    }

}