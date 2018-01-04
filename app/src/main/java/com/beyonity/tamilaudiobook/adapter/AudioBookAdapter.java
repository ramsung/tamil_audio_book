package com.beyonity.tamilaudiobook.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beyonity.tamilaudiobook.R;
import com.beyonity.tamilaudiobook.bean.AudioBook;
import com.beyonity.tamilaudiobook.utils.Utils;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Wiz@rd on 11/23/2016.
 */
public class AudioBookAdapter extends RecyclerView.Adapter<AudioBookAdapter.ViewHolder> {

    private Context context;
    private List<AudioBook> items;
    private IAdapterItemClick<AudioBook> callback;

    public AudioBookAdapter(Context context, ArrayList<AudioBook> items,
                            IAdapterItemClick<AudioBook> callback) {
        this.context = context;
        this.items = items;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_audiobook, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final AudioBook book = items.get(position);
        holder.name.setText(book.getAlbumName());
        holder.author.setText(book.getArtistName());
        int h = (int) ((book.getDuration() / 1000) / 3600);
        int m = (int) (((book.getDuration() / 1000) / 60) % 60);
        int percent = new Random().nextInt(100);
        int progressColor = Color.rgb(200 - (percent * 2), percent * 2, 0);
        holder.progressTxt.setTextColor(progressColor);
        holder.progressTxt.setText(percent + "%");
        holder.progress.setDonut_progress(String.valueOf(percent));
        holder.progress.setFinishedStrokeColor(progressColor);
        holder.duration.setText(String.format(Locale.ENGLISH, "%02dhr%02dm", h, m));
        if (book.getAlbumArt() != null) {
            Picasso.with(context).load(Utils.getUriOfMedia(book.getAlbumId()))
                    .placeholder(R.drawable.ic_default_artist)
                    .resizeDimen(R.dimen.size_64dp, R.dimen.size_64dp)
                    .centerCrop()
                    .into(holder.photo);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null)
                    callback.onItemSelected(holder.getAdapterPosition(),
                            items.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateList(ArrayList<AudioBook> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, author, duration, progressTxt;
        private final DonutProgress progress;
        private final CircleImageView photo;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            author = (TextView) itemView.findViewById(R.id.author);
            duration = (TextView) itemView.findViewById(R.id.duration);
            progressTxt = (TextView) itemView.findViewById(R.id.progress_text);
            progress = (DonutProgress) itemView.findViewById(R.id.donut_progress);
            photo = (CircleImageView) itemView.findViewById(R.id.profile_image);
        }
    }
}

