package sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private Validator(){};

    public static String passwordErrorMessage = "Password must contain at least 1 of (0-9, a-z, A-Z) and must have a length between 8 and 40 characters!";
    public static boolean validatePasswordIntegrity(String password)
    {
        Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,40}$");
        Matcher matcher = p.matcher(password);
        return matcher.matches();
    }
    public static boolean validateEmailFormat(String email)
    {
        Pattern p = Pattern.compile("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(email);
        return matcher.matches();
    }

    public static boolean validateKeyFormat(String key)
    {
        Pattern p = Pattern.compile("^(?=\\S+$).{50}$");
        Matcher matcher = p.matcher(key);
        return matcher.matches();
    }




}
