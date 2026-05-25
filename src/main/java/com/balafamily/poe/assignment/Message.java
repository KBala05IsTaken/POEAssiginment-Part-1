/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.balafamily.poe.assignment;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author KhanyisaB
 */
class Message 
{
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Static counter for all sent messages
    private static int totalMessagesSent = 0;

    // Constructor
    public Message(int messageNumber,
                       String recipient,
                       String messageText)
    {
        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;

        this.messageHash = createMessageHash();
    }

    // Generate random 10-digit message ID
    private String generateMessageID()
    {
        Random rand = new Random();

        long number =
                1000000000L +
                (long)(rand.nextDouble() * 9000000000L);

        return String.valueOf(number);
    }

    // Validate recipient
    public boolean checkRecipientCell()
    {
        return recipient.matches("\\+\\d{1,3}\\d{1,10}");
    }

    // Validate message length
    public boolean checkMessage()
    {
        return messageText.length() <= 250;
    }

    // Create message hash
    private String createMessageHash()
    {
        String firstTwo =
                messageID.substring(0, 2);

        String[] words =
                messageText.trim().split("\\s+");

        String firstWord =
                words[0];

        String lastWord =
                words[words.length - 1];

        String hash =
                firstTwo +
                ":" +
                messageNumber +
                ":" +
                firstWord +
                lastWord;

        return hash.toUpperCase();
    }

    // Print full message details
    public String printMessage()
    {
        return """
               -------------------------
               Message ID: %s
               Message Hash: %s
               Recipient: %s
               Message: %s
               -------------------------
               """.formatted(
                messageID,
                messageHash,
                recipient,
                messageText);
    }

    // Send/store/disregard message
    public String sentMessage()
    {
        if (!checkRecipientCell())
        {
            return "Cell number is incorrectly formatted.";
        }

        if (!checkMessage())
        {
            return "Please enter a message of less than 250 characters.";
        }

        Scanner input = new Scanner(System.in);

        System.out.println("""
                
                Choose an option:
                1) Send Message
                2) Disregard Message
                3) Store Message to send later
                """);

        int choice =
                Integer.parseInt(input.nextLine());

        switch (choice)
        {
            case 1:

                totalMessagesSent++;

                return "Message successfully sent";

            case 2:

                return "Press 0 to delete the message";

            case 3:

                return "Message successfully stored";

            default:

                return "Invalid option selected.";
        }
    }

    // Return total messages sent
    public static int returnTotalMessages()
    {
        return totalMessagesSent;
    }
}
