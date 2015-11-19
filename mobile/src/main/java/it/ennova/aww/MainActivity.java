package it.ennova.aww;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import it.ennova.aww.communicationlayer.CommunicationLayer;
import it.ennova.aww.communicationlayer.GoogleApiWrapper;

import static android.support.v4.app.NotificationCompat.*;

/**
 * TIP: http://developer.android.com/training/wearables/notifications/creating.html
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, CommunicationLayer {

    private Notification notification;
    private NotificationManagerCompat notificationManager;
    private final int NOTIFICATION_REQUEST_ID = 1;
    private GoogleApiClient googleApiClient;
    private Bitmap backgroundBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        findViewById(R.id.showNotificationButton).setOnClickListener(this);
        findViewById(R.id.addPageToNotificationButton).setOnClickListener(this);

        notificationManager = NotificationManagerCompat.from(this);
        backgroundBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_notification_background);

        googleApiClient = new GoogleApiWrapper<>().build(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        googleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        googleApiClient.disconnect();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showNotificationButton:
                addNotification();
                break;
            case R.id.addPageToNotificationButton:
                addPageToNotification();
                break;
        }
    }

    /**
     * Makes the notification
     */
    private void addNotification() {
        Action action = new Action.Builder(R.mipmap.ic_launcher, "WTT", null).build();

        WearableExtender wearableExtender = new WearableExtender()
                .setBackground(backgroundBitmap)
                .addAction(action);

        notification = new Builder(this)
                .setContentTitle("Workshop")
                .setContentText("Wearable Tech Torino")
                .setSmallIcon(R.mipmap.ic_launcher)
                .extend(wearableExtender)
                .build();

        notificationManager.notify(NOTIFICATION_REQUEST_ID, notification);
    }

    /**
     * Adds pages to a notification
     */
    private void addPageToNotification() {

    }

    /**
     * You can use the communication APIs here
     */
    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
