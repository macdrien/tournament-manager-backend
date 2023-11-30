package fr.sidranie.auth;

/**
 * An object identifying a user for authentication.
 * 
 * The identifier field can refer to the username or the mail address
 */
public class Credential {
    public String identifier;
    public String password;
}
