package com.example.kumpr4a.todoapp.ui;

import android.content.Context;

/**
 * Created by kumpr4a on 6/2/2017.
 */

public class Utility {

    public static int dpToPx(int dp, Context context) {
        return (int) (dp * context.getResources().getSystem().getDisplayMetrics().density);
    }

}
