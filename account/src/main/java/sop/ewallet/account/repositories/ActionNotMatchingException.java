package sop.ewallet.account.repositories;

public class ActionNotMatchingException extends Exception{
    public ActionNotMatchingException(String message) {
        super(message);
    }
    public ActionNotMatchingException(){super("Incorrect action");}
}
