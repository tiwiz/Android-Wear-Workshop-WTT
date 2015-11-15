package it.ennova.aww;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.WearableListView;

public class MainActivity extends WearableActivity {

    private final static String[] conferences = {"Wearable Tech Torino", "Droidcon Italy 2016",
            "View Conference", "#FutureDecoded", "Codemotion Milan", "Torino Mini Maker Faire",
            "Torino Film Festival", "SMAU"};

    private WearableListView wearableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        wearableListView = (WearableListView) findViewById(R.id.conference_list_view);
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    /**
     * Do something here
     */
    private void updateDisplay() {
        if (isAmbient()) {

        } else {

        }
    }
}
