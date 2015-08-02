package com.rakuishi.memol.util;

import android.view.MenuItem;

/**
 * Created by rakuishi on 15/08/02.
 */
public class MenuItemUtils {

    private static final String TAG = MenuItemUtils.class.getSimpleName();

    private MenuItemUtils() {}

    public static void setEnable(MenuItem menuItem, boolean enable) {
        if (menuItem == null) {
            return;
        }

        menuItem.setEnabled(enable);

        if (menuItem.getIcon() != null) {
            int alpha = enable ? 255 : 127;
            menuItem.getIcon().setAlpha(alpha);
        }
    }
}
