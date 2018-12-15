package com.example.dhaivat.foodlogger.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by foram on 28/2/17.
 */

public class AppHelper {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final boolean DEBUG_MODE = true;
    private static final String TAG = "[RIHAAPP]";
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
           };


    public static boolean verifyStoragePermissions(Activity activity) {

        boolean givePermission = false;

        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
            givePermission = false;
        } else {
            givePermission = true;
        }
        return givePermission;
    }

//    public static void getUserDetailFromServer(final String token, final Context context, final Runnable runnable, final Runnable runnableNoInternetNoStoredUser) {
//        final RestClient client = new RestClient(context);
//        RetrofitServiceInteface retrofitServiceInteface = client.setUpInterface();
//        retrofitServiceInteface.userDetails(token, new Callback<Response>() {
//            @Override
//            public void success(Response response, Response response2) {
//                try {
//                    JSONObject obj = client.getObjectFromResponse(response);
//                    User user = new GsonBuilder().create().fromJson(obj.toString(), User.class);
//                    user.setToken(token);
//                    ComplexPrefUtils.clearToken(context);
//                    ComplexPrefUtils.setuser(user, context);
//                    new Handler().post(runnable);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
//                    if (ComplexPrefUtils.getUser(context) == null)
//                        new Handler().post(runnableNoInternetNoStoredUser);
//                    else
//                        new Handler().post(runnable);
//                } else {
//                    client.showErrorMessage(error);
//                }
//            }
//        });
//
//    }


    public static void setFullBrightness(Context context, Activity activity) {
        int mode = 0;
        if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
            //Automatic mode
            mode = 1;
        } else {
            //Manual mode
            mode = 0;
        }
        /*try {
            //sets manual mode and brightnes 255
            Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);  //this will set the manual mode (set the automatic mode off)
            Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 255);  //this will set the brightness to maximum (255)

            //refreshes the screen
            int br = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.screenBrightness = (float) br / 255;
            activity.getWindow().setAttributes(lp);

        } catch (Exception e) {}*/
    }

//
//    public static String getUserToken(Context context) {
//        return ComplexPrefUtils.getUser(context).getToken();
//    }


    public static void logE(String key, String value) {
        if (DEBUG_MODE) {
            Log.e(key, value + "");
        }
    }

    public static void logE(String value) {
        if (DEBUG_MODE) {
            Log.e(TAG, value + "");
        }
    }

    public static void logE(int value) {
        if (DEBUG_MODE) {
            Log.e(TAG, value + "");
        }
    }






}
