package test.lhz.com.testanimator.networklogs;

import android.annotation.TargetApi;
import android.app.admin.DevicePolicyManager;
import android.app.admin.NetworkEvent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DeviceAdminReceiver extends android.app.admin.DeviceAdminReceiver{

    private static final String TAG = "DeviceAdminReceiver";

    /*
     * TODO: reconsider how to store and present the logs in the future, e.g. save the file into
     * internal memory and show the content in a ListView
     */
    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void onNetworkLogsAvailable(Context context, Intent intent, long batchToken, int networkLogsCount) {
        Log.i(TAG, "onNetworkLogsAvailable(), batchToken: " + batchToken + ", event count: " + networkLogsCount);
        DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        List<NetworkEvent> events = null;
        try {
            events = dpm.retrieveNetworkLogs(getComponentName(context), batchToken);
        } catch (SecurityException e) {
            Log.e(TAG, "Exception while retrieving network logs batch with batchToken: " + batchToken, e);
        }
        if (events == null) {
            Log.e(TAG, "Failed to retrieve network logs batch with batchToken: " + batchToken);
            return;
        }
//        Toast.makeText(context, context.getString(R.string.on_network_logs_available_success, batchToken), Toast.LENGTH_LONG).show();
        ArrayList<String> loggedEvents = new ArrayList<String>();
        events.forEach(new Consumer<NetworkEvent>() {
            @Override
            public void accept(NetworkEvent event) {
                Log.e(TAG,event.toString());
//                loggedEvents.add(event.toString());
            }
        });
//        new EventSavingTask(context, loggedEvents).execute();
    }

    /**
     * @param context The context of the application.
     * @return The component name of this component in the given context.
     */
    public static ComponentName getComponentName(Context context) {
        return new ComponentName(context.getApplicationContext(), DeviceAdminReceiver.class);
    }
}
