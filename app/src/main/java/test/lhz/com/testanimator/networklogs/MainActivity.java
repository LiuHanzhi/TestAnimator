package test.lhz.com.testanimator.networklogs;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.app.admin.NetworkEvent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends Activity {

    final static String ACTION_NETWORK_LOGS_AVAILABLE =
            "com.android.cts.deviceowner.action.ACTION_NETWORK_LOGS_AVAILABLE";

    private DeviceAdminReceiver deviceAdminReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        DevicePolicyManager dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
//        dpm.setNetworkLoggingEnabled(DeviceAdminReceiver.getComponentName(this),true);

        deviceAdminReceiver = new DeviceAdminReceiver();
        final IntentFilter filterNetworkLogsAvailable = new IntentFilter(
                ACTION_NETWORK_LOGS_AVAILABLE);
        registerReceiver(deviceAdminReceiver, filterNetworkLogsAvailable);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(deviceAdminReceiver);
    }
}
