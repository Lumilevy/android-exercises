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

public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final LayoutInflater mInflater;
    private final List<Book> mBooks;
    private FragmentListBooks.OnBookListClick mListener;

    public BookAdapter(LayoutInflater inflater, List<Book> books, FragmentListBooks.OnBookListClick listener){
        mInflater = inflater;
        mBooks = books;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.fragment_list_view,parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        BookItemView bookItemView = (BookItemView) holder.itemView;
        bookItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onBookClick(mBooks.get(position));
            }
        });

        bookItemView.bindView(mBooks.get(position));
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
