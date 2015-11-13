package it.ennova.aww;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * TIP: http://developer.android.com/training/wearables/notifications/creating.html
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        findViewById(R.id.showNotificationButton).setOnClickListener(this);
        findViewById(R.id.addPageToNotificationButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
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

    }

    /**
     * Adds pages to a notification
     */
    private void addPageToNotification() {

    }
}
