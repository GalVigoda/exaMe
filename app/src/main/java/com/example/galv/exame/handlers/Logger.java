package com.example.galv.exame.handlers;

import android.util.Log;

public class Logger {
    public final static String LOG_TAG = "exaMe_APP_LOG";

    public Logger (){

    }

    public static void ReportError (String funcName, String error){
        Log.e(LOG_TAG, GetMessage(funcName, error));
    }

    public static void ReportWarning (String funcName, String error){
        Log.w(LOG_TAG, GetMessage(funcName, error));
    }

    public static void ReportInfo (String funcName, String error){
        Log.i(LOG_TAG, GetMessage(funcName, error));
    }

    public static void ReportDebug (String funcName, String error){
        Log.d(LOG_TAG, GetMessage(funcName, error));
    }

    private static String GetMessage (String funcName, String error){
        return funcName + ": " + error;
    }
}
