package com.hjj.my.app161015.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;



/**
 * Created by 1027 on 2016-10-22.
 */

public class Phone {
    private Context context;
    private Activity activity; //Relation\

    public Phone(Context context, Activity activity){
        this.context = context;
        this.activity = activity;
    }
    public void dial(String phoneNumber){  //걸기전에 물어보기
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
        context.startActivity(intent);
    }
    public void directCall(String phoneNumber){ //물어보지 않고 다이렉트로걸기
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CALL_PHONE},2);
            return;
        }
        context.startActivity(intent);
    }
}
