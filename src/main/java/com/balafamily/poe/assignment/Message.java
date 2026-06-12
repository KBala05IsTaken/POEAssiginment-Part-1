/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.balafamily.poe.assignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



class Message 
{
    // Arrays required by assignment
    public static ArrayList<String> sentMessages = new ArrayList<>();
    public static ArrayList<String> disregardedMessages = new ArrayList<>();
    public static ArrayList<String> storedMessages = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    
    private static final String FILE_NAME = "Message.txt";
    
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Static counter for all sent messages
    private static int totalMessagesSent = 0;
    
    //These getters are required for the searching mechanic
    public String getMessageID()
    {
        return messageID;
    }

    public String getRecipient()
    {
        return recipient;
    }

    public String getMessageText()
    {
        return messageText;
    }

    public String getMessageHash()
    {
        return messageHash;
    }

    // Constructor
    public Message(int messageNumber, String recipient, String messageText)
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

        int choice =Integer.parseInt(input.nextLine());
        switch(choice)
        {
            case 1:

                totalMessagesSent++;

                sentMessages.add(messageText);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);

                return "Message successfully sent";

            case 2:

                disregardedMessages.add(messageText);

                return "Message disregarded";

            case 3:

                storedMessages.add(messageText);

                messageHashes.add(messageHash);
                messageIDs.add(messageID);

                saveMessageToFile();

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
    
    //Saving the stored message
    public void saveMessageToFile()
    {
        try
        {
            FileWriter fw = new FileWriter(FILE_NAME, true);

            fw.write(
                    messageID + "|" +
                    messageHash + "|" +
                    recipient + "|" +
                    messageText + "\n");

            fw.close();
        }
        catch(IOException e)
        {
            System.out.println(
                    "Error saving message.");
        }
    }
    
    //This loads the stored message to the array
    public static void loadStoredMessages()
    {
        storedMessages.clear();

        try
        {
            File file = new File(FILE_NAME);

            if(!file.exists())
            {
                return;
            }

            Scanner reader = new Scanner(file);

            while(reader.hasNextLine())
            {
                storedMessages.add(reader.nextLine());
            }

            reader.close();
        }
        catch(Exception e)
        {
            System.out.println("Error loading stored messages.");
        }
    }
    
    //================
    //Option 3 Sub Menu
    //================
    
    //Displays the sender and the recipient
    public static void displaySendersRecipients()
    {
        loadStoredMessages();

        for(String msg : storedMessages)
        {
            String[] data = msg.split("\\|");

            System.out.println("Recipient: " + data[2]);
        }
    }
    
    //Displays the longest message
    public static void displayLongestMessage()
    {
        loadStoredMessages();

        String longest = "";

        for(String msg : storedMessages)
        {
            String[] data = msg.split("\\|");

            if(data[3].length() > longest.length())
            {
                longest = data[3];
            }
        }

        System.out.println("Longest Message:");
        System.out.println(longest);
    }
    
    //This searches using the messageID
    public static void searchByMessageID(String searchID)
    {
        loadStoredMessages();

        for(String msg : storedMessages)
        {
            String[] data =
                    msg.split("\\|");

            if(data[0].equals(searchID))
            {
                System.out.println(
                        "Recipient: "
                        + data[2]);

                System.out.println(
                        "Message: "
                        + data[3]);

                return;
            }
        }

        System.out.println(
                "Message not found.");
    }
    
    //Searches by recipient
    public static void searchByRecipient(String recipient)
    {
        loadStoredMessages();

        boolean found = false;

        for(String msg : storedMessages)
        {
            String[] data =
                    msg.split("\\|");

            if(data[2].equals(recipient))
            {
                System.out.println(
                        data[3]);

                found = true;
            }
        }

        if(!found)
        {
            System.out.println(
                    "No messages found.");
        }
    }
    
    //This deletes the message by using hash
    public static void deleteByHash(String hash)
    {
        loadStoredMessages();

        ArrayList<String> updated =
                new ArrayList<>();

        boolean deleted = false;

        for(String msg : storedMessages)
        {
            String[] data = msg.split("\\|");

            if(data[1].equals(hash))
            {
                deleted = true;
            }
            else
            {
                updated.add(msg);
            }
        }

        try
        {
            FileWriter fw = new FileWriter(FILE_NAME);

            for(String line : updated)
            {
                fw.write(line + "\n");
            }

            fw.close();
        }
        catch(IOException e)
        {
            System.out.println("Error deleting message.");
        }

        if(deleted)
        {
            System.out.println("Message deleted.");
        }
        else
        {
            System.out.println("Hash not found.");
        }
    }
    
    //Displays the full report
    public static void displayReport()
    {
        loadStoredMessages();

        System.out.println(
                "\nSTORED MESSAGE REPORT\n");

        for(String msg : storedMessages)
        {
            String[] data =
                    msg.split("\\|");

            System.out.println(
                    "Message ID: "
                    + data[0]);

            System.out.println(
                    "Hash: "
                    + data[1]);

            System.out.println(
                    "Recipient: "
                    + data[2]);

            System.out.println(
                    "Message: "
                    + data[3]);

            System.out.println(
                    "-------------------");
        }
    }
}
