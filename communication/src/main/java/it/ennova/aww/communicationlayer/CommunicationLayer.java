package it.ennova.aww.communicationlayer;

import com.google.android.gms.common.api.GoogleApiClient;

public interface CommunicationLayer extends GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

        String MESSAGE_PATH = "/clickedItem";
}
