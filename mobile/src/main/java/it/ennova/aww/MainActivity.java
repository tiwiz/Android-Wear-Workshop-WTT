package it.ennova.aww;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.List;

import it.ennova.aww.communicationlayer.CommunicationLayer;
import it.ennova.aww.communicationlayer.GoogleApiWrapper;

import static android.support.v4.app.NotificationCompat.Action;
import static android.support.v4.app.NotificationCompat.Builder;
import static android.support.v4.app.NotificationCompat.WearableExtender;

/**
 * TIP: http://developer.android.com/training/wearables/notifications/creating.html
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, CommunicationLayer, MessageApi.MessageListener {

    private Notification notification;
    private NotificationManagerCompat notificationManager;
    private GoogleApiClient googleApiClient;
    private Bitmap backgroundBitmap;
    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        findViewById(R.id.showNotificationButton).setOnClickListener(this);
        findViewById(R.id.addPageToNotificationButton).setOnClickListener(this);
        responseText = (TextView) findViewById(R.id.messageApiResult);

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

        int NOTIFICATION_REQUEST_ID = 1;
        notificationManager.notify(NOTIFICATION_REQUEST_ID, notification);
    }

    /**
     * Adds pages to a notification
     */
    private void addPageToNotification() {

        WearableExtender extender = new WearableExtender().addPages(getNotificationPages());

        notification = new Builder(this)
                .setContentTitle("Workshop")
                .setContentText("Working with pages")
                .setSmallIcon(R.mipmap.ic_launcher)
                .extend(extender)
                .build();

        int NOTIFICATION_REQUEST_ID_PAGES = 2;
        notificationManager.notify(NOTIFICATION_REQUEST_ID_PAGES, notification);
    }

    private List<Notification> getNotificationPages() {

        List<Notification> pages = new ArrayList<>(3);

        for (int i = 0; i < 3; i++) {

            NotificationCompat.BigTextStyle additionalPageStyle = new NotificationCompat.BigTextStyle();
            additionalPageStyle.setBigContentTitle("Additional page")
                    .bigText(String.format("This is a very long text representing iteration number %d", i + 1));

            pages.add(new NotificationCompat.Builder(this).setStyle(additionalPageStyle).build());
        }

        return pages;
    }

    /**
     * You can use the communication APIs here
     */
    @Override
    public void onConnected(Bundle bundle) {
        Wearable.MessageApi.addListener(googleApiClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if (messageEvent.getPath().equals(CommunicationLayer.MESSAGE_PATH)) {
            responseText.setText(new String(messageEvent.getData()));
        }
    }
}
