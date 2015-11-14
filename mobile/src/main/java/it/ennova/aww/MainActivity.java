package it.ennova.aww;

import android.app.Notification;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * TIP: http://developer.android.com/training/wearables/notifications/creating.html
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Notification notification;
    private NotificationManagerCompat notificationManager;
    private final int NOTIFICATION_REQUEST_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        findViewById(R.id.showNotificationButton).setOnClickListener(this);
        findViewById(R.id.addPageToNotificationButton).setOnClickListener(this);

        notificationManager = NotificationManagerCompat.from(this);
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
        notification = new NotificationCompat.Builder(this)
                .setContentTitle("Workshop")
                .setContentText("Wearable Tech Torino")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        notificationManager.notify(NOTIFICATION_REQUEST_ID, notification);
    }

    /**
     * Adds pages to a notification
     */
    private void addPageToNotification() {

    }
}
