package com.developers.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qsmaxmin.qsbase.common.aspect.Permission;
import com.qsmaxmin.qsbase.common.utils.permission.PermissionUtils;
import com.qsmaxmin.qsbase.mvp.QsActivity;

public class MainActivity extends QsActivity {

    private Button btn_call;

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    /**
     * 跳转拨打电话界面
     * @param phoneNum
     */
//    public void callPhone(String phoneNum) {
//        Intent intent = new Intent(Intent.ACTION_DIAL);
//        Uri data = Uri.parse("tel:" + phoneNum);
//        intent.setData(data);
//        startActivity(intent);
//    }

    /**
     * 拨打电话
     * @param phoneNum
     */
    @Permission(value = {Manifest.permission.CALL_PHONE})
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }
    @Override
    public void initData(Bundle savedInstanceState) {
        btn_call = findViewById(R.id.btn_call);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone("13213507915");
            }
        });
    }
}
