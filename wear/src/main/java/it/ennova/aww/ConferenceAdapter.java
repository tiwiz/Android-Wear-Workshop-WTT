package it.ennova.aww;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ConferenceAdapter extends RecyclerView.Adapter<WearableViewHolder> {

    private final OnRecyclerViewItemClick listener;
    private final static String[] conferences = {"Wearable Tech Torino", "Droidcon Italy 2016",
            "View Conference", "#FutureDecoded", "Codemotion Milan", "Torino Mini Maker Faire",
            "Torino Film Festival", "SMAU"};

    public ConferenceAdapter(OnRecyclerViewItemClick listener) {
        this.listener = listener;
    }

    @Override
    public WearableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.conference_item, parent, false);
        return new WearableViewHolder(rootView, listener);
    }

    @Override
    public void onBindViewHolder(WearableViewHolder holder, int position) {
        holder.bindTo(conferences[position]);
    }

    @Override
    public int getItemCount() {
        return conferences.length;
    }
}
