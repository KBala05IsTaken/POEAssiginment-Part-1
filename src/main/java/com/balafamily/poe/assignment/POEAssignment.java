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
        Scanner input = new Scanner(System.in);

        LoginClass objLogin = new LoginClass();

        // =========================
        // REGISTRATION
        // =========================

        String username;
        String password;
        String phoneNumber;

        // USERNAME
        do
        {
            System.out.println("""
                    Create Username
                    Must contain '_' and be no more than 5 characters.
                    """);

            username = input.nextLine();

            if (!objLogin.checkUsername(username))
            {
                System.out.println("Username incorrectly formatted.");
            }

        } while (!objLogin.checkUsername(username));

        System.out.println("Username successfully captured.");

        // PASSWORD
        do
        {
            System.out.println("""
                    Create Password
                    Must contain:
                    - 8 characters
                    - Capital letter
                    - Number
                    - Special character
                    """);

            password = input.nextLine();

            if (!objLogin.checkPasswordComplexity(password))
            {
                System.out.println("Password incorrectly formatted.");
            }

        } while (!objLogin.checkPasswordComplexity(password));

        System.out.println("Password successfully captured.");

        // PHONE NUMBER
        do
        {
            System.out.println("Enter phone number");
            System.out.println("Example: +27831234567");

            phoneNumber = input.nextLine();

            if (!objLogin.checkCellPhoneNumber(phoneNumber))
            {
                System.out.println("Cell phone number incorrectly formatted.");
            }

        } while (!objLogin.checkCellPhoneNumber(phoneNumber));

        System.out.println("Phone number successfully added.");

        // REGISTER USER
        System.out.println(
                objLogin.registerUser(username, password));

        // =========================
        // LOGIN
        // =========================

        System.out.println("Enter username:");
        String loginUser = input.nextLine();

        System.out.println("Enter password:");
        String loginPass = input.nextLine();

        boolean isLoggedIn =
                objLogin.loginUser(loginUser, loginPass);

        System.out.println(
                objLogin.returnLoginStatus());

        // =========================
        // QUICKCHAT SYSTEM
        // =========================

        if (isLoggedIn)
        {
            System.out.println("Welcome to QuickChat.");

            System.out.println(
                    "How many messages would you like to send?");

            int maxMessages =
                    Integer.parseInt(input.nextLine());

            int sentMessages = 0;

            int option;

            do
            {
                System.out.println("""
                        
                        QUICKCHAT MENU
                        
                        1) Send Messages
                        2) Show recently sent messages
                        3) Quit
                        """);

                option =
                        Integer.parseInt(input.nextLine());

                switch (option)
                {
                    case 1:

                        if (sentMessages < maxMessages)
                        {
                            System.out.println(
                                    "Enter recipient number:");

                            String recipient =
                                    input.nextLine();

                            System.out.println(
                                    "Enter your message:");

                            String messageText =
                                    input.nextLine();

                            Message objMessage =
                                    new Message(
                                            sentMessages + 1,
                                            recipient,
                                            messageText);

                            String result =
                                    objMessage.sentMessage();

                            System.out.println(result);

                            // Only count valid messages
                            if (result.equals("Message successfully sent"))
                            {
                                sentMessages++;

                                System.out.println(
                                        objMessage.printMessage());
                            }
                        }
                        else
                        {
                            System.out.println(
                                    "You have reached your message limit.");
                        }

                        break;

                    case 2:

                        System.out.println("Coming Soon.");

                        break;

                    case 3:

                        System.out.println("""
            
                        Application Closed.
                        Total messages sent: """
                        + Message.returnTotalMessages());

                        break;

                    default:

                        System.out.println("Invalid menu option.");
                }

            } while (option != 3);
        }

        input.close();
    }
}
