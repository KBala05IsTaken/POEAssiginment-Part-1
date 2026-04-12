/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.balafamily.poe.assignment;
/**
 *
 * @author KhanyisaB
 */
public class LoginClass 
{
    private String Username;
    private String Password;
    private boolean isLoginSuccessful;
    
    public boolean checkUsername(String sName)       
    {
        boolean bValid = false;
        if (sName.length() <= 5)
            {
                if (sName.contains("_"))
                {
                    bValid = true;
                }
            }
        else
        {
            bValid = false;
        }
        return bValid;
    }
    
    public boolean checkPasswordComplexity(String sPass)
    {
        boolean bPassValid = false;
        
        boolean bPassCap = sPass.matches(".*[A-Z].*");              // Checks password if it contain's capital letters
        int iPassLength = sPass.length();                           // Checks password's length
        boolean bPassSpec = sPass.matches(".*[^a-zA-Z0-9].*");      // Checks password for special values
        boolean bPassNum = sPass.matches(".*\\d.*");             // Checks password for numbers
            
        if (iPassLength >= 8)
        {
            if (bPassCap == true)
            {
                if (bPassSpec == true)
                {
                    if (bPassNum == true)
                    {
                        bPassValid = true; 
                            System.out.println("Login password is valid");
                    }
                 }
                }
            }
            else  
            {
              bPassValid = false;
            }
        return bPassValid;
    }
    
    public boolean checkCellPhoneNumber(String sPhone)
    {   
        boolean bPhoneValid;
        String regex = "\\+\\d{1,3}\\d{10}";

        if (sPhone.matches(regex)) 
        {
            bPhoneValid = true;        
        } 
        else 
        {                                                            
            bPhoneValid = false;             
        } 
        return bPhoneValid;
    }
    
    public String registerUser(String sName, String sPass)
    {
        boolean isValid;
        
        isValid = checkUsername(sName);
        if (!isValid)
            return "The username is no correctly formatted";
        
        isValid = checkPasswordComplexity(sPass);
        if (!isValid)
            return "The password does not meet the complexity standard";
        
        Username = sName;
        Password = sPass;
        
        return "The user has been registered";
    }
    
    public boolean loginUser(String sName, String sPass)
    {
        if ((Username.equals(sName)) && (Password.equals(sPass)))
        {
            isLoginSuccessful = true;
            return true;
        }
        else
        {
            isLoginSuccessful = false;
            return false;
        }
    }
    
    public String returnLoginStatus()
    {
        String sLogin;
        if (isLoginSuccessful)
        {
             sLogin = "Login was successful";
             System.out.print(sLogin);
        }
        else 
        {
            sLogin  = "Failed to login";
            System.out.print(sLogin);
        }
        
        return sLogin;
    }
}
