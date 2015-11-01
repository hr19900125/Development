package com.ryan.toolbox.tool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.ryan.toolbox.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class Common {

    /**
     * check EditText is empty or not
     *
     * @param edText pass EditText for check is empty or not
     * @return true or false
     */
    public static boolean isEmptyEditText(EditText edText) {
        if (edText.getText().toString().trim().length() > 0)
            return false;
        else
            return true;
    }

    /**
     * check the email address is valid or not.
     *
     * @param email pass email id in string
     * @return true when its valid otherwise false
     */
    public static boolean isEmailIdValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }

    /**
     * Function to display simple Alert Dialog or Toast
     *
     * @param context - application context
     * @param title   - alert dialog title
     * @param message - alert message
     * @param toast   - show as toast or dialog
     */
    public static void showAlertDialog(Context context, String title, String message, boolean toast) {
        if (toast) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        } else {

            AlertDialog alertDialog = null;
            if (!((Activity) context).isFinishing()) {
                if (alertDialog == null)
                    alertDialog = new AlertDialog.Builder(context).create();
                // Setting Dialog Title
                alertDialog.setTitle(title);
                // Setting Dialog Message
                alertDialog.setMessage(message);
                // Setting OK Button
                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                // Showing Alert Message
                alertDialog.show();
            }
        }
    }

    /**
     * check availability of Internet
     *
     * @param context
     * @return true or false
     */
    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    /**
     * Get today's date in any format.
     *
     * @param dateFormat pass format for get like: "yyyy-MM-dd hh:mm:ss"
     * @return current date in string format
     */
    public static String getCurrentDate(String dateFormat) {
        Date d = new Date();
        String currentDate = new SimpleDateFormat(dateFormat).format(d.getTime());
        return currentDate;
    }

    /**
     * Use for getting your device id if available.
     *
     * @param context
     * @return your device id
     */
    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * get bitmap which you stored in preference
     *
     * @param mContext
     * @param name            of bitmap preference
     * @param PREFS_FILE_NAME
     * @return bitmap image
     */
    public static Bitmap getBitmapFromPreference(Context mContext, String name, String PREFS_FILE_NAME) {
        try {
            String imageString = getStringPrefrences(mContext, name, PREFS_FILE_NAME);
            byte[] encodeByte = Base64.decode(imageString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    /**
     * set String Preference Value
     *
     * @param context
     * @param prefName Preference name
     * @param Value    Preference value
     */
    public static void setStringPrefrences(Context context, String prefName, String Value, String PREFS_FILE_NAME) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(prefName, Value);
        editor.commit();
    }

    /**
     * get String Preference Value
     *
     * @param context
     * @param prefName
     * @return
     */
    public static String getStringPrefrences(Context context, String prefName, String PREFS_FILE_NAME) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        if (prefs.contains(prefName))
            return prefs.getString(prefName, null);
        else
            return "";
    }

    // -----------------------------------------------

    /**
     * set Integer Preference Value
     *
     * @param context
     * @param prefName
     * @param Value
     */
    public static void setIntPrefrences(Context context, String prefName, int Value, String PREFS_FILE_NAME) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(prefName, Value);
        editor.commit();
    }

    // -----------------------------------------------

    /**
     * get Integer Preference Value
     *
     * @param context
     * @param prefName
     * @return
     */
    public static int getIntPrefrences(Context context, String prefName, String PREFS_FILE_NAME) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(prefName, 0);
    }

    // -----------------------------------------------

    /**
     * set Boolean Preference Value
     *
     * @param context
     * @param prefName
     * @param Value
     */
    public static void setBooleanPrefrences(Context context, String prefName, Boolean Value, String PREFS_FILE_NAME) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(prefName, Value);
        editor.commit();
    }

    // -----------------------------------------------

    /**
     * get Boolean Preference Value
     *
     * @param context
     * @param prefName
     * @return
     */
    public static boolean getBooleanPrefrences(Context context, String prefName, String PREFS_FILE_NAME) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(prefName, false);
    }

    // -----------------------------------------------

    /**
     * set Float Preference Value
     *
     * @param context
     * @param prefName
     * @param Value
     */
    public static void setFloatPrefrences(Context context, String prefName, Float Value, String PREFS_FILE_NAME) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(prefName, Value);
        editor.commit();
    }

    // -----------------------------------------------
    // static File SDCardRoot = Environment.getExternalStorageDirectory();

    /**
     * get Float Preference Value
     *
     * @param context
     * @param prefName
     * @return
     */
    public static float getFloatPrefrences(Context context, String prefName, String PREFS_FILE_NAME) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getFloat(prefName, 0);
    }

    // ------------------------------------------------

    /**
     * set Long Preference Value
     *
     * @param context
     * @param prefName
     * @param Value
     */
    public static void setLongPrefrences(Context context, String prefName, Long Value, String PREFS_FILE_NAME) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(prefName, Value);
        editor.commit();
    }

    // -----------------------------------------------

    /**
     * get Long Preference Value
     *
     * @param context
     * @param prefName
     * @return
     */
    public static long getLongPrefrences(Context context, String prefName, String PREFS_FILE_NAME) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getLong(prefName, 0);
    }

    /**
     * remove all the preferences of your app. Note: only remove all which set
     * by using this sdk.
     *
     * @param context
     */
    public static void removeAllPrefrences(Context context, String PREFS_FILE_NAME) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * using for getting your current location
     *
     * @param context
     * @return current location
     */
    @SuppressWarnings("static-access")
    public static Location getCurrentLocation(Context context) {
        Location location = null;
        try {
            LocationManager locationManager = (LocationManager) context
                    .getSystemService(context.LOCATION_SERVICE);

            // getting GPS status
            boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            System.out.println("gps band chhe" + isGPSEnabled);
            // getting network status
            boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            double latitude , longitude;
            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
//                Common.showGPSDisabledAlert("Please enable your location or connect to cellular network.", context);
            } else {
                if (isNetworkEnabled) {
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    public static void showGPSDisabledAlert(String msg, final Context ctx) {
        AlertDialog alert;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
        alertDialogBuilder.setMessage(msg).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent callGPSSettingIntent = new Intent(
                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        ctx.startActivity(callGPSSettingIntent);
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alert = alertDialogBuilder.create();
        alert.show();
    }

    public static void showNETWORDDisabledAlert(final Context ctx) {
        AlertDialog alert;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
        alertDialogBuilder.setMessage("Let the app use data connectivity.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent callGPSSettingIntent = new Intent(
                                android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
                        ctx.startActivity(callGPSSettingIntent);
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alert = alertDialogBuilder.create();
        alert.show();
    }

    /**
     * use for getting application Icon.
     *
     * @param mContext
     * @return Icon as drawable from the application
     */
    public static Drawable getAppIcon(Context mContext) {
        Drawable icon = null;
        final PackageManager pm = mContext.getPackageManager();
        String packageName = mContext.getPackageName();
        try {
            icon = pm.getApplicationIcon(packageName);
            return icon;
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    /**
     * use for make local notification from application
     *
     * @param mContext
     * @param title    for the Notification
     * @param message  for the notification
     * @param mIntent  for open activity to open on touch of notification
     */
    @SuppressLint("NewApi")
    @SuppressWarnings({"static-access"})
    public static void sendLocatNotification(Context mContext, String title,
                                             String message, Intent mIntent) {
        System.out.println("called: " + title + " : " + message);
        int appIconResId = 0;
        PendingIntent pIntent = null;
        if (mIntent != null)
            pIntent = PendingIntent.getActivity(mContext, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        final PackageManager pm = mContext.getPackageManager();
        String packageName = mContext.getPackageName();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = pm.getApplicationInfo(packageName,
                    PackageManager.GET_META_DATA);
            appIconResId = applicationInfo.icon;
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // Notification notification = new Notification.Builder(mContext)
        // .setSmallIcon(appIconResId).setWhen(System.currentTimeMillis())
        // .setContentTitle(title).setContentText(message)
        // .setContentIntent(pIntent).getNotification();

        Notification notification;
        if (mIntent == null) {
            notification = new Notification.Builder(mContext)
                    .setSmallIcon(appIconResId).setWhen(System.currentTimeMillis())
                    .setContentTitle(message)
                    .setStyle(new Notification.BigTextStyle().bigText(message))
                    .setAutoCancel(true)
                    .setContentText(message)
                    .setContentIntent(PendingIntent.getActivity(mContext, 0, new Intent(), 0))
                    .getNotification();

        } else {
            notification = new Notification.Builder(mContext)
                    .setSmallIcon(appIconResId).setWhen(System.currentTimeMillis())
                    .setContentTitle(message)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setStyle(new Notification.BigTextStyle().bigText(message))
                    .setContentIntent(pIntent).getNotification();
        }
        // Remove the notification on click
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        // Play default notification sound
        notification.defaults |= Notification.DEFAULT_SOUND;

        // Vibrate if vibrate is enabled
        notification.defaults |= Notification.DEFAULT_VIBRATE;

        NotificationManager manager = (NotificationManager) mContext
                .getSystemService(mContext.NOTIFICATION_SERVICE);
        // manager.notify(0, notification);
        manager.notify(R.string.app_name, notification);
    }

    public static char getRandomCharacter() {
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');

        return c;
    }

    // ------------------------------

    private static KeyguardManager.KeyguardLock lock;

    /**
     * use for make disable sleep screen lock while application in use.
     * 让屏幕保持不暗
     * @param mContext
     */
    @SuppressWarnings({"static-access"})
    public static void disableSleepMode(Context mContext) {
        System.out.println("disable");
        ((Activity) mContext).getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        KeyguardManager keyguardManager = (KeyguardManager) mContext
                .getSystemService(Activity.KEYGUARD_SERVICE);
        lock = keyguardManager.newKeyguardLock(mContext.KEYGUARD_SERVICE);
        lock.disableKeyguard();
    }

    // ----------------------------

    /**
     * use for enable sleep screen while using application.
     * 释放屏幕索
     */
    public static void enableSleepMode() {
        lock.reenableKeyguard();
    }

    /**
     * use for oepn any url in browser.
     *
     * @param mContext
     * @param url      to open in your mobile browser
     */
    public static void openURL(Context mContext, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(intent);
    }

    /**
     * use to show address location pin on map.
     *
     * @param mContext
     * @param address  to show on map.
     */
    public static void showAddressOnMap(Context mContext, String address) {
        address = address.replace(' ', '+');
        Intent geoIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + address));
        mContext.startActivity(geoIntent);
    }

}