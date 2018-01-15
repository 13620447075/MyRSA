package com.example.administrator.myrsa;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class MainActivity extends AppCompatActivity {
    private View view;
    private static final String TAG = "TAG";
    KeyPair keyPair=RsaUitls.generateRSAKeyPair(RsaUitls.DEFAULT_KEY_SIZE);
    // 公钥
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
    // 私钥
    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    private String texts="sufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgysufdasdh6437eftwsgy";

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);

        Log.e(TAG,"privateKey-----:"+privateKey);
        Log.e(TAG,"publicKey-----:"+publicKey);
        try {
            byte[] encryptBytes=RsaUitls.encryptByPublicKeyForSpilt(texts.getBytes(),publicKey.getEncoded());
            String encryStr=Base64Utils.encode(encryptBytes);
            Log.e(TAG,"加密——"+encryStr);
            byte[] decryptBytes=  RsaUitls.decryptByPrivateKeyForSpilt(Base64Utils.decode(encryStr),privateKey.getEncoded());
            String decryStr=new String(decryptBytes);
            Log.e(TAG,"解密——"+decryStr);

            if (decryStr.equals(texts)){
                Log.e(TAG,"一样");
            }else {
                Log.e(TAG,"不一样");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void recreate() {
        super.recreate();

    }

    public void rijian(View view) {
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        recreate();
    }

    public void yejian(View view) {
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        recreate();
    }
}
