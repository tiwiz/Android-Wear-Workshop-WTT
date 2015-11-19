package it.ennova.aww;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.WearableListView;
import android.widget.ViewFlipper;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.List;

import it.ennova.aww.communicationlayer.CommunicationLayer;
import it.ennova.aww.communicationlayer.GoogleApiWrapper;

public class MainActivity extends WearableActivity
        implements CommunicationLayer, OnRecyclerViewItemClick<String>, ResultCallback<NodeApi.GetConnectedNodesResult> {

    private ViewFlipper viewFlipper;
    private GoogleApiClient googleApiClient;
    private List<Node> nodesList;
    private final ResultCallback messageSentCallback = new MessageCallback();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        viewFlipper = (ViewFlipper) findViewById(R.id.mainViewFlipper);
        WearableListView wearableListView = (WearableListView) findViewById(R.id.conference_list_view);
        wearableListView.setAdapter(new ConferenceAdapter(this));
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
            viewFlipper.showNext();
        } else {
            viewFlipper.showPrevious();
        }
    }

    /**
     * You can use the communication API here
     */
    @Override
    public void onConnected(Bundle bundle) {
        Wearable.NodeApi.getConnectedNodes(googleApiClient).setResultCallback(this);
    }

    @Override
    public void onResult(NodeApi.GetConnectedNodesResult result) {
        nodesList = result.getNodes();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onItemClick(String itemData) {
        if (nodesList != null) {
            forwardMessageToNodes(itemData);
        }
    }

    private void forwardMessageToNodes(String itemData) {
        for (Node node : nodesList) {
            Wearable.MessageApi.sendMessage(googleApiClient, node.getId(),
                    CommunicationLayer.MESSAGE_PATH, itemData.getBytes()).setResultCallback(messageSentCallback);
        }
    }

    private class MessageCallback implements ResultCallback<MessageApi.SendMessageResult> {

        @Override
        public void onResult(MessageApi.SendMessageResult sendMessageResult) {

        }
    }
}
