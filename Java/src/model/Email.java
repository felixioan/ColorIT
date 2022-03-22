package model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an Email.
 * @author Group 6 - 1Y ICT A2020
 * @version 1.0 - December 2020
 * @since 1.0
 */
public class Email implements Serializable
{
    private String email;

    /**
     * One-argument constructor.
     * Creates an email object if the email is valid. Uses isEmailValid.
     * @param email Contains the email.
     */
    public Email(String email)
    {
        if (isEmailValid(email))
            this.email = email;
        else
            throw new IllegalArgumentException("The email is not valid");
    }

    /**
     * Gets the email.
     * @return A string representing the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email, if its valid. Uses isEmailValid.
     * @param email A string containing the email.
     */
    public void setEmail(String email) {
        if (isEmailValid(email.trim()))
            this.email = email.trim();
        else throw new IllegalArgumentException("The email is not valid");
    }

    /**
     * Returns the answer of comparison of two objects.
     * @param email Contains the the email we want to compare to.
     * @return True if the objects are the same. False if they are different.
     */
    public boolean equals(Email email)
    {
        if (!(email instanceof Email))
            return false;

        /*Email o = (Email)email;*/
        return email.getEmail().equals(this.email);
    }

    /**
     * Analyzes the string and says if its valid.
     * @param email Contains the email we want to validate.
     * @return The result of the validation. True if its valid. False if its not valid.
     */
    public static boolean isEmailValid(String email)
    {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher1 = pattern.matcher(email);
        return matcher1.matches();
    }
}
