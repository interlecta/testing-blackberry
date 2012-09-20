package com.f1rst.blackberry.log;

import net.rim.device.api.system.EventLogger;

/**
 * A class for debugging.
 * @author ivaylo
 */
public class Logger {

    private static StringBuffer debugBuffer = new StringBuffer(5000);

    /**
     * for production version set this variable to false
     */
    public static boolean DEBUG = true;
    
    /**
     * It writes the given string in the system log
     * @param logMessage
     */
    public static void log(String appName, String logMessage) {
        if (DEBUG) {
            System.out.println(logMessage);
            EventLogger.register(0x9a60491923523456L, appName, EventLogger.VIEWER_STRING);
            EventLogger.logEvent(0x9a60491923523456L, logMessage.getBytes());
        }
    }

    public static void log(String logMessage) {
        if (DEBUG) {
            System.out.println(logMessage);
            EventLogger.register(0x9a60491923523456L, "BlackBerry Application", EventLogger.VIEWER_STRING);
            EventLogger.logEvent(0x9a60491923523456L, logMessage.getBytes());
            getDebugBuffer().insert(0, "\n");
            getDebugBuffer().insert(0,logMessage);
        }
    }

    public static StringBuffer getDebugBuffer() {
        return debugBuffer;
    }

}
