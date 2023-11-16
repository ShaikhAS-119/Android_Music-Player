package com.example.musicapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
    public List<String> data;
    MediaPlayer mediaPlayer;
    //String[] data;
    Context context;

    public MyAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.listelement,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(data.get(position));
        //play button
        holder.play.setOnClickListener(view -> {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayer.setDataSource(data.get(position));
                mediaPlayer.prepare();
                mediaPlayer.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //pause button
        holder.pause.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
            }
            else if (!mediaPlayer.isPlaying()){
                Toast.makeText(context, "MEDIA is not playing", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
}
    class  ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
    Button play,pause;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textview);
            play=itemView.findViewById(R.id.playIcon);
            pause=itemView.findViewById(R.id.pauseIcon);
        }
}

