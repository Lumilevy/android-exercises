package fr.android.androidexercises;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import timber.log.Timber;

public class LibraryActivity extends AppCompatActivity implements FragmentListBooks.OnBookListClick {

    private Book mBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        Timber.plant(new Timber.DebugTree());

        FragmentListBooks fragmentListBooks;

        if(savedInstanceState != null){
            fragmentListBooks = (FragmentListBooks) getSupportFragmentManager().findFragmentByTag(FragmentListBooks.class.getSimpleName());

            mBook = savedInstanceState.getParcelable("BOOK");

            displayBookDetail();
        } else {
            fragmentListBooks = new FragmentListBooks();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFrameLayout, fragmentListBooks, FragmentListBooks.class.getSimpleName())
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("BOOK", mBook);
    }

    @Override
    public void onBookClick(Book book) {
        mBook = book;
        displayBookDetail();
    }

    private void displayBookDetail(){
        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);

        FragmentBook detailFragment = new FragmentBook();

        Bundle bundle = new Bundle();
        bundle.putParcelable("BOOK", mBook);

        detailFragment.setArguments(bundle);

        if(isLandscape){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.bookDetail, detailFragment, FragmentBook.class.getSimpleName())
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerFrameLayout, detailFragment, FragmentBook.class.getSimpleName())
                    .addToBackStack("ComingFromList")
                    .commit();
        }

    }
}
