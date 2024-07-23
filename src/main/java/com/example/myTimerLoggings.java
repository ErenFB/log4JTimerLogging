package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class myTimerLoggings {
    private static final Logger logger = LogManager.getLogger(myTimerLoggings.class);

    public static void main(String[] args) {
        Timer debugTimer = new Timer();
        Timer infoTimer = new Timer();
        Timer errorTimer = new Timer();

        // Debug
        debugTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                logger.debug(String.format("Debug - Current time: %tT", System.currentTimeMillis()));
            }
        }, 0, 1000);

        // Info
        infoTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                logger.info(String.format("Info - Current time: %tR", System.currentTimeMillis()));
            }
        }, getInitialDelayForMinute(), 60000);

        // Error
        errorTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                logger.error(String.format("Error - Current time: %tR", System.currentTimeMillis()));
            }
        }, getInitialDelayForHour(), 3600000);
    }

    // Calculate initial delay to start logging at the beginning of the next minute
    private static long getInitialDelayForMinute() {
        Calendar calendar = Calendar.getInstance();
        int seconds = calendar.get(Calendar.SECOND);
        int milliseconds = calendar.get(Calendar.MILLISECOND);
        return (60 - seconds) * 1000 - milliseconds;
    }
    // Calculate initial delay to start logging at the beginning of the next hour
    private static long getInitialDelayForHour() {
        Calendar calendar = Calendar.getInstance();
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        int milliseconds = calendar.get(Calendar.MILLISECOND);
        return (60 - minutes) * 60000 - seconds * 1000 - milliseconds;
    }
}
