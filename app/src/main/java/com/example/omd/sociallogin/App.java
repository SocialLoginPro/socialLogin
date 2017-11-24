package com.example.omd.sociallogin;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.facebook.FacebookSdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Delta on 07/09/2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.omd.sociallogin", PackageManager.GET_SIGNATURES);
            for (Signature signature :info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
                Toast.makeText(this,Base64.encodeToString(md.digest(),Base64.DEFAULT)+"", Toast.LENGTH_SHORT).show();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
