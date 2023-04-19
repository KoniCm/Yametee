package com.praticing.yametee;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>
{
    private Activity activity;
    private Context context;
    private ArrayList book_id, book_title, book_author,book_genre,book_publish, book_pages;

    Animation animation;

    CustomAdapter(Activity activity, Context context, ArrayList book_id, ArrayList book_title, ArrayList book_author,
                  ArrayList book_genre,ArrayList book_publish,
                  ArrayList book_pages)
    {
        this.activity = activity;
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_genre = book_genre;
        this.book_publish = book_publish;
        this.book_pages = book_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position)
    {
        holder.book_title_txt.setText(String.valueOf(book_title.get(position)));
        holder.book_author_txt.setText(String.valueOf(book_author.get(position)));
        holder.book_genre_txt.setText(String.valueOf(book_genre.get(position)));
        holder.book_publish_txt.setText(String.valueOf(book_publish.get(position)));
        holder.book_pages_txt.setText(String.valueOf(book_pages.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.details_edit, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId())
                        {
                            case R.id.edit_menu:
                                Intent intent = new Intent(context, UpdateActivity.class);
                                intent.putExtra("id", String.valueOf(book_id.get(position)));
                                intent.putExtra("title", String.valueOf(book_title.get(position)));
                                intent.putExtra("author", String.valueOf(book_author.get(position)));
                                intent.putExtra("genre", String.valueOf(book_genre.get(position)));
                                intent.putExtra("publish", String.valueOf(book_publish.get(position)));
                                intent.putExtra("pages", String.valueOf(book_pages.get(position)));
                                activity.startActivityForResult(intent, 1);
                                break;
                            case R.id.view_menu:
                                Intent viewIntent = new Intent(context, Details.class);
                                viewIntent.putExtra("id", String.valueOf(book_id.get(position)));
                                viewIntent.putExtra("title", String.valueOf(book_title.get(position)));
                                viewIntent.putExtra("author", String.valueOf(book_author.get(position)));
                                viewIntent.putExtra("genre", String.valueOf(book_genre.get(position)));
                                viewIntent.putExtra("publish", String.valueOf(book_publish.get(position)));
                                viewIntent.putExtra("pages", String.valueOf(book_pages.get(position)));
                                activity.startActivityForResult(viewIntent, 1);
                                break;
                            default:
                                return false;
                        }
                        return false;
                    }
                });
            }
        });
        holder.mainLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView book_title_txt, book_author_txt,book_genre_txt,book_publish_txt,book_pages_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_title_txt = itemView.findViewById(R.id.book_title_txt);
            book_author_txt = itemView.findViewById(R.id.book_author_txt);
            book_genre_txt = itemView.findViewById(R.id.book_genre_txt);
            book_publish_txt = itemView.findViewById(R.id.book_publish_txt);
            book_pages_txt = itemView.findViewById(R.id.book_pages_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            //Simple Animation
            animation = AnimationUtils.loadAnimation(context, R.anim.anim_trans);
            mainLayout.setAnimation(animation);
        }
    }
}