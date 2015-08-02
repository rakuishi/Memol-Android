package com.rakuishi.memol.util;

import android.util.Log;

import com.rakuishi.memol.BuildConfig;

import static com.rakuishi.memol.Config.PACKAGE_PREFIX;

/**
 * Created by rakuishi on 15/08/02.
 */
public class LogUtils {

    private static final String LOG_PREFIX = PACKAGE_PREFIX + "log.";

    private LogUtils() {}

    public static void LOGD(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(LOG_PREFIX + tag, message);
        }
    }
}
