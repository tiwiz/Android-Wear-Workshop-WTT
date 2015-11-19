package it.ennova.aww;


import android.support.annotation.NonNull;
import android.support.wearable.view.WearableListView;
import android.view.View;
import android.widget.TextView;

public class WearableViewHolder extends WearableListView.ViewHolder implements View.OnClickListener{

    private OnRecyclerViewItemClick<String> listener;
    private TextView captionLabel;

    public WearableViewHolder(@NonNull View itemView,
                              @NonNull OnRecyclerViewItemClick<String> listener) {
        super(itemView);
        this.listener = listener;
        itemView.setOnClickListener(this);
        captionLabel = (TextView) itemView.findViewById(R.id.conference_name);
    }

    public void bindTo(@NonNull String caption) {
        captionLabel.setText(caption);
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(captionLabel.getText().toString());
    }
}
