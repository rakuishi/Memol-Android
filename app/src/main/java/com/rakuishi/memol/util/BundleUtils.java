package com.rakuishi.memol.util;

import android.os.Bundle;

/**
 * Created by rakuishi on 15/08/02.
 */
public class BundleUtils {

    private BundleUtils() {}

    public static long getLong(Bundle bundle, String key) {
        if (bundle != null && bundle.containsKey(key)) {
            return bundle.getLong(key, 0L);
        }

        return 0L;
    }
}
