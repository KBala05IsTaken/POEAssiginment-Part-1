/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.balafamily.poe.assignment;
import java.util.Scanner;

/**
 *
 * @author KhanyisaB
 */
public class POEAssignment {

    public static void main(String[] args) 
    {
        LoginClass objLogin = new LoginClass();
        Scanner scnInput = new Scanner(System.in);
        String sPassword = "", sUsername = "";
        
        
        boolean bPasswordValid = false;
        boolean bUserValid = false; 
        
        //Check if the username has the proper inputs
        while (bUserValid == false)
        {
            System.out.println("Welcome user. Please enter your username: ");
            sUsername = scnInput.next();
            int iUserLength = sUsername.length();
            
            if (iUserLength <= 5)//Checks the length of iUserLength
            {
                if (sUsername.contains("_"))//Checks if sUserName contains an underscore
                {
                    bUserValid = true;//Saves the 
                    System.out.println("Username succsessfully captured");
                }
            }
            else //Will loop through this part if the bUserValid boolean remains false
            {
                System.out.println("Username is not correctly formatted; please ensure that your username"
                        + " contains an underscore and is no more than five characters in length");
            }
        }
     //Checks if the password is valid   
     while (bPasswordValid == false) 
        {
            System.out.println("Next, Please enter your password: ");
            sPassword = scnInput.next();
            boolean bPassCap = sPassword.matches(".*[A-Z].*");              // Checks password if it contain's capital letters
            int iPassLength = sPassword.length();                           // Checks password's length
            boolean bPassSpec = sPassword.matches(".*[^a-zA-Z0-9].*");      // Checks password for special values
            boolean bPassNum = sPassword.matches(".*\\d.*");             // Checks password for numbers
            
            if (iPassLength >= 8)
            {
                if (bPassCap == true)
                {
                    if (bPassSpec == true)
                    {
                        if (bPassNum == true)                        {

                            bPasswordValid = true; 
                            System.out.println("Password successFully captured");
                        }
                    }
                }
            }
            else if (bPasswordValid == false)
            {
              System.out.println("Password is not correctly formatted;"
                      + " please insure that the password contains at least "
                      + "eight characters, a capital letter, a special character and numbers"); 
            } 
        }
         //Checks if the phone number is valid
         System.out.println("Type in your country code which is then followed by your phone number");
         String sPhoneNumber = scnInput.next();

        // Regex: starts with +, followed by 1–3 digits (country code), then 10 digits (phone number)
        String regex = "\\+\\d{1,3}\\d{10}";

        if (sPhoneNumber.matches(regex)) 
        {
            System.out.println("Valid international phone number.");        
        } 
        else 
        {                                                           
            System.out.println("Invalid phone number format.");            
        }   
        
        String sLoginUser; 
        String sLoginPass = "";
        
        //Makes the user login to the system after they are done inputing
        System.out.println("Welcome user. Please re-enter your username again to ;ogin: ");
        sLoginUser = scnInput.next();
        if(sLoginUser == sUsername)
        {
            System.out.println("Welcome user. Please enter your username: ");
            sLoginPass = scnInput.next();
        }
        
        objLogin.checkUsername(sUsername);
        objLogin.checkPasswordComplexity(sPassword);
        objLogin.checkCellPhoneNumber(sPhoneNumber);
        objLogin.registerUser(sUsername, sPassword);
        objLogin.loginUser(sLoginUser, sLoginPass);
        objLogin.returnLoginStatus();
    }
}
