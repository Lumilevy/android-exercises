package fr.android.androidexercises;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class FragmentListBooks extends Fragment {

    private OnBookListClick listener;
    private BookAdapter bookAdapter;
    private ArrayList<Book> books = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (OnBookListClick) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookAdapter = new BookAdapter(LayoutInflater.from(getContext()), books, listener);

        RecyclerView recyclerView = view.findViewById(R.id.bookRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(bookAdapter);

        if (savedInstanceState != null){
            books.clear();
            ArrayList<Book> booksToAdd = savedInstanceState.getParcelableArrayList("BOOKS");
            books.addAll(booksToAdd);

            bookAdapter.notifyDataSetChanged();

        } else if (books.isEmpty()){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://henri-potier.xebia.fr/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            final BookService bookService = retrofit.create(BookService.class);

            Call<List<Book>> listCall = bookService.getBooks();
            Timber.i("Retrieving books from WebService");

            listCall.enqueue(new Callback<List<Book>>() {
                @Override
                public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                    books.clear();
                    List<Book> booksToAdd = response.body();
                    books.addAll(booksToAdd);

                    bookAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<Book>> call, Throwable t) {
                    Timber.e(t, "Error while trying to get books");
                }
            });
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("BOOKS", books);
    }

    public interface OnBookListClick {
        public void onBookClick(Book book);
    }
}
