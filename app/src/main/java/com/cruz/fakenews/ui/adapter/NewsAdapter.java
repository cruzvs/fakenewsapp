package com.cruz.fakenews.ui.adapter;

import static com.cruz.fakenews.ui.NewsActivity.Extra.THE_LIKE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cruz.fakenews.databinding.CardNewsBinding;
import com.cruz.fakenews.domain.News;
import com.cruz.fakenews.ui.NewsActivity;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<News> newss;

    public NewsAdapter(List<News> newss) {
        this.newss = newss;
    }

    public List<News> getNewss(){
        return newss;
    }


    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardNewsBinding binding = CardNewsBinding.inflate(layoutInflater, parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        News news = newss.get(position);
        Intent intent = new Intent(context,NewsActivity.class);


        Glide.with(context).load(news.getImage()).into(holder.binding.imgCard);
        holder.binding.title.setText(news.getTitle());
        holder.binding.subtitle.setText(news.getSubtitle());
        holder.binding.imgCard.setOnClickListener(view -> context.startActivity(intent));
        holder.binding.btnCheck.setOnClickListener(view -> context.startActivity(intent));


        intent.putExtra(NewsActivity.Extra.NEWS,news);
    }

    @Override
    public int getItemCount() {
        return newss.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final CardNewsBinding binding;

        public ViewHolder(CardNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }


}
