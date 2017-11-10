package fr.android.androidexercises;


import android.view.View;

public interface ItemView<T> {
    View bind(T element);
}