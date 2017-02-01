package in.nfluence.fcm;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;



/**
 * Created by ${ajinkya} on ${2016-04-04}.
 */
public class RegistrationIntentService extends IntentService {
    private static final String TAG = "RegIntentService";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String error = null;
        try {
            synchronized (TAG) {


              /*  boolean development = true;
                KiiUser.pushInstallation(development).install(token);*/

            }
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            error = e.getLocalizedMessage();
        }
        Intent registrationComplete = new Intent("in.nfluence.fcm.COMPLETED");
        registrationComplete.putExtra("ErrorMessage", error);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
    }
}