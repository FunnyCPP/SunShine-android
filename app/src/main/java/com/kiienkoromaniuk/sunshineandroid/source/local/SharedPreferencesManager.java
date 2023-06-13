package com.kiienkoromaniuk.sunshineandroid.source.local;

import static com.kiienkoromaniuk.sunshineandroid.source.local.SharedPreferencesManager.Key.DEFAULT_USERNAME;

import android.content.Context;
import android.content.SharedPreferences;

import javax.annotation.Nullable;
import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class SharedPreferencesManager {
    private final SharedPreferencesUtil sharedPreferencesUtil;
    private final Context context;

    @Inject
    public SharedPreferencesManager(@ApplicationContext Context context, SharedPreferencesUtil sharedPreferencesUtil) {
        this.context = context;
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    public void save(final String value, final Key key) {
        sharedPreferencesUtil.savePreference(context, key.code, value);
    }

    public void save(final int value, final Key key) {
        sharedPreferencesUtil.savePreference(context, key.code, value);
    }

    public void save(final float value, final Key key) {
        sharedPreferencesUtil.savePreference(context, key.code, value);
    }

    public void save(final long value, final Key key) {
        sharedPreferencesUtil.savePreference(context, key.code, value);
    }

    public void save(final boolean value, final Key key) {
        sharedPreferencesUtil.savePreference(context, key.code, value);
    }

    public String load(Key key) {
        return sharedPreferencesUtil.loadString(context, key.code);
    }

    @Nullable
    public String loadStringOrNull(Key key) {
        String stringLoaded = load(key);
        if (stringLoaded.equals(SharedPreferencesUtil.DEFAULT_STRING_VALUE)) {
            return null;
        }
        return stringLoaded;
    }

    public void remove(Key key) {
        sharedPreferencesUtil.removePreference(context, key.code);
    }

    public int loadInt(Key key) {
        return sharedPreferencesUtil.loadInt(context, key.code);
    }

    @Nullable
    public Integer loadIntOrNull(Key key) {
        int intLoaded = loadInt(key);
        if (intLoaded == SharedPreferencesUtil.DEFAULT_INT_VALUE) {
            return null;
        }
        return intLoaded;
    }

    public float loadFloat(Key key) {
        return sharedPreferencesUtil.loadFloat(context, key.code);
    }

    public long loadLong(Key key) {
        return sharedPreferencesUtil.loadLong(context, key.code);
    }

    @Nullable
    public Long loadLongOrNull(Key key) {
        long longLoaded = loadLong(key);
        if (longLoaded == SharedPreferencesUtil.DEFAULT_LONG_VALUE) {
            return null;
        }
        return longLoaded;
    }

    public boolean loadBoolean(Key key) {
        return sharedPreferencesUtil.loadBoolean(context, key.code);
    }

    public void clear() {
        String defaultUsername = load(DEFAULT_USERNAME);
        sharedPreferencesUtil.removeAllPreference(context);
        save(defaultUsername, DEFAULT_USERNAME);
    }

    public void registerListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferencesUtil.registerOnSharedPreferencesChangedListener(context, listener);
    }

    public void unregisterListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferencesUtil.unregisterOnSharedPreferencesChangedListener(context, listener);
    }

    public enum Key {
        HOST_API("HOST_API"),
        DEFAULT_USERNAME("DEFAULT_USERNAME"),
        DOMAIN("DOMAIN"),
        TOKEN_TYPE("TOKEN_TYPE"),
        EXPIRES_IN("EXPIRES_IN"),
        ACCESS_TOKEN("ACCESS_TOKEN"),
        REFRESH_TOKEN("REFRESH_TOKEN"),
        CREATED_AT("CREATED_AT"),
        ;

        private final String code;

        Key(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
