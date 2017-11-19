package fr.android.androidexercises;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookItemView extends LinearLayout {

    private TextView nameTextView;
    private TextView priceTextView;
    private ImageView coverImageView;

    public BookItemView(Context context) {
        this(context, null);
    }

    public BookItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BookItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        nameTextView = (TextView) findViewById(R.id.titleTextView);
        priceTextView = (TextView) findViewById(R.id.priceTextView);
        coverImageView = (ImageView) findViewById(R.id.coverImageView);
    }

    public void bindView(Book book) {
        nameTextView.setText(book.getTitle());
        priceTextView.setText(String.valueOf(book.getPrice()));
        Glide.with(this)
                .load(book.getCover())
                .into(coverImageView);
    }
}
