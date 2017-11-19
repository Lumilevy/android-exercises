package fr.android.androidexercises;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lumi on 19/11/2017.
 */

public interface BookService {

    @GET("books")
    Call<List<Book>> getBooks();
}
