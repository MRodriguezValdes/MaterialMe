package com.example.android.materialme;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;

class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<Sport> mSportsData;
    private Context mContext;

    SportsAdapter(Context context, ArrayList<Sport> sportsData) {
        this.mSportsData = sportsData;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    // Método para actualizar la lista de datos después de reordenar
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mSportsData, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sport currentSport = mSportsData.get(position);

        // Set other TextViews
        holder.mTitleText.setText(currentSport.getTitle());
        holder.bindTo(currentSport);
    }

    @Override
    public int getItemCount() {
        return mSportsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleText;
        private ImageView mSportsImage;
        private TextView mInfoText;

        ViewHolder(View itemView) {
            super(itemView);
            mTitleText = itemView.findViewById(R.id.title);
            mSportsImage = itemView.findViewById(R.id.sportsImage);
            mInfoText = itemView.findViewById(R.id.subTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Obtener el objeto Sport para el elemento clicado
            int position = getAdapterPosition();
            Sport currentSport = mSportsData.get(position);

            // Crear un Intent para lanzar la DetailActivity
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", currentSport.getTitle());
            detailIntent.putExtra("image_resource", currentSport.getImageResource());
            detailIntent.putExtra("subTitle", currentSport.getInfo());
            // Iniciar la DetailActivity
            mContext.startActivity(detailIntent);
        }

        void bindTo(Sport currentSport) {
            // Populate the textviews with data.
            mTitleText.setText(currentSport.getTitle());
            mInfoText.setText(currentSport.getInfo());
            Glide.with(mContext).load(currentSport.getImageResource()).into(mSportsImage);
        }
    }
}
