package it.ennova.aww.communicationlayer;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Wearable;

public class GoogleApiWrapper<T extends Context & CommunicationLayer> {

    public GoogleApiClient build (@NonNull T base) {

        return new GoogleApiClient.Builder(base)
                .addConnectionCallbacks(base)
                .addOnConnectionFailedListener(base)
                .addApi(Wearable.API)
                .build();
    }
}
