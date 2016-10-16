package com.example.api.restfull;

/**
 * Created by Administrator on 10/11/2016.
 */

public class PushNotificationServerException extends Exception {

    public PushNotificationServerException(String message)
    {
        super(message);
    }

    public PushNotificationServerException(Exception e)
    {
        super(e);
    }
}
