package com.rk_softwares.bdlinkhub.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import androidx.security.crypto.MasterKeys;

public class SecureStorge {

    private final SharedPreferences preferences;

    public SecureStorge(Context context){

        try {

            MasterKey key = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            preferences = EncryptedSharedPreferences.create(context,
                    "secure_pref",
                    key,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void putString(String key, String value){

        preferences.edit().putString(key, value).apply();

    }

    public String getString(String key){

        return preferences.getString(key, null);
    }

    public void remove_key(String key){

        preferences.edit().remove(key).apply();

    }

    public void clear_all(){

        preferences.edit().clear().apply();

    }

}
