package fr.android.androidexercises;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FragmentBook extends Fragment {

    private Book mBook;
    private TextView titleTextView;
    private TextView priceTextView;
    private TextView isbnTextView;
    private TextView synopsisTextView;
    private ImageView coverImageView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);

        mBook = getArguments().getParcelable("BOOK");

        titleTextView = view.findViewById(R.id.titleTextView);
        priceTextView = view.findViewById(R.id.priceTextView);
        isbnTextView = view.findViewById(R.id.isbnTextView);
        synopsisTextView = view.findViewById(R.id.synopsisTextView);

        coverImageView = view.findViewById(R.id.coverImageView);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(mBook != null){
            titleTextView.setText(mBook.getTitle());
            priceTextView.setText(String.format("%s €", mBook.getPrice()));
            isbnTextView.setText(String.format("Isbn : %s", mBook.getIsbn()));
            synopsisTextView.setText(mBook.getSynopsis());

            Glide.with(view.getContext())
                    .load(mBook.getCover())
                    .into(coverImageView);
        } else {
            coverImageView.setImageResource(0);
            titleTextView.setText("");
            priceTextView.setText("");
            isbnTextView.setText("");

            synopsisTextView.setText(R.string.no_book_selected);
        }
    }
}
