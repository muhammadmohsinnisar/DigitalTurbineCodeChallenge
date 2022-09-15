package com.example.digitalturbinedtwalltest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DTService extends Service {
    private static final String TAG = "DT_Service";
    public NotificationManager notificationManager;
    public String adId;
    public boolean limitedAdTracking;
    Notification builder;
    SharedPreferences sharedPreferences;
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    private Handler notificationHandler;

    public DTService() {
    }

    public static long currentTimeSecsUTC() {
        return Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                .getTimeInMillis() / 1000;
    }

    public static String haskeyGenerator(String format, String appId, String googleID, Boolean limitedTracked, String ip, String locale, String page, String pub, long timestamp, String uid, String hashkey) {
        String myhashkey = "offers." + format + "?" + "appid=" + appId + "&google_ad_id=" + googleID + "&google_ad_id_limited_tracking_enabled=" + limitedTracked + "&ip=" + ip + "&locale=" + locale + "&page=" + page + "&pub0=" + pub + "&timestamp=" + timestamp + "&uid=" + uid + "&hashkey=" + hashkey;
        return myhashkey;
    }

    public static String stringGenerator(String appId, String googleID, Boolean limitedTracked, String ip, String locale, String page, String pub, long timestamp, String uid, String token) {
        String key = "appid=" + appId + "&google_ad_id=" + googleID + "&google_ad_id_limited_tracking_enabled=" + limitedTracked + "&ip=" + ip + "&locale=" + locale + "&page=" + page + "&pub0=" + pub + "&timestamp=" + timestamp + "&uid=" + uid + "&" + token;
        return key;
    }

    public static String getSha1Hex(String clearString) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(clearString.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = messageDigest.digest();
            StringBuilder buffer = new StringBuilder();
            for (byte b : bytes) {
                buffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return buffer.toString();
        } catch (Exception ignored) {
            ignored.printStackTrace();
            return null;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "DT service created");
        String adId;


        AdvertisingIdClient.getAdvertisingId(this, new com.example.digitalturbinedtwalltest.AdvertisingIdClient.Listener() {
            @Override
            public void onAdvertisingIdClientFinish(com.example.digitalturbinedtwalltest.AdvertisingIdClient.AdInfo adInfo) {
                final String adId = adInfo.getId();
                Log.d(TAG, "AD ID Found: " + adId);
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("adId", adId).apply();
                limitedAdTracking = adInfo.isLimitAdTrackingEnabled();
                Log.d(TAG, "AD ID limited tracking: " + limitedAdTracking);
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean("LimitedTrack", limitedAdTracking).apply();
            }

            @Override
            public void onAdvertisingIdClientFail(Exception exception) {
                Log.d(TAG, "Ad ID not found!");
            }

        });


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "DT Service Started!");
        notificationManager = getSystemService(NotificationManager.class);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //create intent
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);


        builder =
                new Notification.Builder(this, "DTWall_Notification_Channel")
                        .setContentTitle(getText(R.string.app_name))
                        .setContentText(getText(R.string.user_Login))
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentIntent(pendingIntent)
                        .setTicker(getText(R.string.application_Id))
                        .build();

        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (Objects.equals(key, "switch_dt")) {
                    if (sharedPreferences.getBoolean(key, false)) {
                        Log.d(TAG, "Switch On DTWall");
                        setupDTWall();
                    } else {
                        Log.d(TAG, "DTWall Failed");
                    }
                }
            }
        };
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);

        // Start foreground service.

        if (sharedPreferences.getBoolean("switch_dt", false)) {
            setupDTWall();
        }


        return START_STICKY;
    }

    private void setupDTWall() {
        String sp_appId = sharedPreferences.getString("edp_appId", "123");
        String sp_uid = sharedPreferences.getString("edp_userId", "123");
        String sp_token = sharedPreferences.getString("edp_token", "123");
        String sp_adID = sharedPreferences.getString("adId", "231");
        Boolean sp_limitedTrack = sharedPreferences.getBoolean("LimitedTrack", true);


        String phone_version = getAndroidVersion();
        String ip = getString(R.string.ip_address);
        String locale = getString(R.string.locale);
        String format = getString(R.string.fomat);
        long timestamp = currentTimeSecsUTC();
        String page = getString(R.string.page);
        String pub = getString(R.string.pub0);

        String uniqueID = UUID.randomUUID().toString();
        String pass_mine = stringGenerator(sp_appId, sp_adID, sp_limitedTrack, ip, locale, page, pub, currentTimeSecsUTC(), sp_uid, sp_token);

        Log.d(TAG, "startDTWall");
        Log.d(TAG, "sp appId: " + sp_appId);
        Log.d(TAG, "sp uId: " + sp_uid);
        Log.d(TAG, "sp token: " + sp_token);
        Log.d(TAG, "sp adId: " + sp_adID);

        Log.d(TAG, "phone version: " + getAndroidVersion());
        Log.d(TAG, "AD ID timestamp: " + currentTimeSecsUTC());
        Log.d(TAG, "IP Address: " + ip);
        Log.d(TAG, "String:" + pass_mine);
        String sha1encrpyt = getSha1Hex(pass_mine);
        Log.d(TAG, "String:" + sha1encrpyt);
        String haskey = haskeyGenerator(format, sp_appId, sp_adID, sp_limitedTrack, ip, locale, page, pub, currentTimeSecsUTC(), sp_uid, sha1encrpyt);

        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("end", haskey).apply();


        Log.d(TAG, "final String:" + haskey);
        Log.d(TAG, "Device ID: " + uniqueID);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/v3/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

    }

    private void sendDataToActivity(String haskey) {
        Intent intent = new Intent("SecondFragment");
        intent.putExtra("endPoint", haskey);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }

    private void stopDTWall() {
        Log.d(TAG, "StopDTWall");
    }

    public String getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return "Android SDK: " + sdkVersion + " (" + release + ")";
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
