package com.github.eloyzone.onlineexamination.domain;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 10/7/20, 11:01 AM.
 */
public class ToastMessage
{
    public final static String SUCCESS = "toast-success";
    public final static String ERROR = "toast-error";
    public final static String INFO = "toast-info";
    public final static String WARNING = "toast-warning";

    String message;
    String status;

    public ToastMessage(String message, String status)
    {
        this.message = message;
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
