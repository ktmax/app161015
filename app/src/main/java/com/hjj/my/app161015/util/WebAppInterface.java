package com.hjj.my.app161015.util;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by 1027 on 2016-10-29.
 */

public class WebAppInterface {
    Context context;

    /** Instantiate the interface and set the context */
    public WebAppInterface(Context context) {
        context = context;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
}
