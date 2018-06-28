package org.apache.cordova.power;

import android.content.Context;
import android.os.PowerManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Method;

public class Power extends CordovaPlugin {


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        action = action.toLowerCase();
        if ("reboot".equals(action)) {
            Context mContext = cordova.getActivity().getApplicationContext();
            PowerManager pManager = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
            pManager.reboot(null);
        } else if ("shutdown".equals(action)) {
            try {
                //获得ServiceManager类
                Class ServiceManager = Class.forName("android.os.ServiceManager");
                //获得ServiceManager的getService方法
                Method getService = ServiceManager.getMethod("getService", java.lang.String.class);
                //调用getService获取RemoteService
                Object oRemoteService = getService.invoke(null, Context.POWER_SERVICE);

                //获得IPowerManager.Stub类
                Class<?> cStub = Class.forName("android.os.IPowerManager$Stub");

                //获得asInterface方法
                Method asInterface = cStub.getMethod("asInterface", android.os.IBinder.class);

                //调用asInterface方法获取IPowerManager对象
                Object oIPowerManager = asInterface.invoke(null, oRemoteService);

                //获得shutdown()方法
                Method shutdown = oIPowerManager.getClass().getMethod("shutdown", boolean.class, boolean.class);

                //调用shutdown()方法
                shutdown.invoke(oIPowerManager, false, true);
            } catch (Exception e) {

            }
        }
        return false;
    }
}
