package it.ennova.aww;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.WearableListView;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import it.ennova.aww.communicationlayer.CommunicationLayer;
import it.ennova.aww.communicationlayer.GoogleApiWrapper;

public class MainActivity extends WearableActivity implements CommunicationLayer{

    private final static String[] conferences = {"Wearable Tech Torino", "Droidcon Italy 2016",
            "View Conference", "#FutureDecoded", "Codemotion Milan", "Torino Mini Maker Faire",
            "Torino Film Festival", "SMAU"};

    private WearableListView wearableListView;
    private boolean isConnected = false;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        wearableListView = (WearableListView) findViewById(R.id.conference_list_view);
        googleApiClient = new GoogleApiWrapper<>().build(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
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

    /**
     * You can use the communication API here
     */
    @Override
    public void onConnected(Bundle bundle) {
        isConnected = true;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
