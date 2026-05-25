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
    private String username;
    private String password;
    private boolean isLoginSuccessful;

    // USERNAME VALIDATION
    public boolean checkUsername(String sName)
    {
        return sName.contains("_")
                && sName.length() <= 5;
    }

    // PASSWORD VALIDATION
    public boolean checkPasswordComplexity(String sPass)
    {
        boolean hasCapital =
                sPass.matches(".*[A-Z].*");

        boolean hasNumber =
                sPass.matches(".*\\d.*");

        boolean hasSpecial =
                sPass.matches(".*[^a-zA-Z0-9].*");

        return sPass.length() >= 8
                && hasCapital
                && hasNumber
                && hasSpecial;
    }

    // PHONE NUMBER VALIDATION
    public boolean checkCellPhoneNumber(String sPhone)
    {
        String regex = "\\+\\d{1,3}\\d{10}";

        return sPhone.matches(regex);
    }

    // REGISTER USER
    public String registerUser(String sName, String sPass)
    {
        if (!checkUsername(sName))
        {
            return "Username incorrectly formatted.";
        }

        if (!checkPasswordComplexity(sPass))
        {
            return "Password incorrectly formatted.";
        }

        username = sName;
        password = sPass;

        return "User registered successfully.";
    }

    // LOGIN USER
    public boolean loginUser(String sName, String sPass)
    {
        isLoginSuccessful =
                username.equals(sName)
                && password.equals(sPass);

        return isLoginSuccessful;
    }

    // RETURN LOGIN STATUS
    public String returnLoginStatus()
    {
        if (isLoginSuccessful)
        {
            return "Login successful.";
        }
        else
        {
            return "Login failed.";
        }
    }
}
