package fr.android.androidexercises;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static fr.android.androidexercises.R.*;

/**
 * Created by Lumi on 09/11/2017.
 */

public class BookAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final LayoutInflater mInflater;
    private final List<Book> mBooks;

    public BookAdapter2(LayoutInflater inflater, List<Book> books){
        mInflater = inflater;
        mBooks = books;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(layout.custom_view_item_book,parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BookItemView)holder.itemView).bindView(mBooks.get(position));
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(View itemView){
            super(itemView);
        }
    }
}
