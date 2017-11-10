package fr.android.androidexercises

import android.widget.TextView
import android.widget.LinearLayout
import android.content.Context;
import android.util.AttributeSet;



/**
 * Created by Lumi on 10/11/2017.
 */

class BookItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private var nameTextView: TextView? = null
    private var priceTextView: TextView? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        nameTextView = findViewById(R.id.nameTextView)
        priceTextView = findViewById(R.id.priceTextView)
    }

    fun bindView(book: Book) {
        nameTextView!!.text = book.name
        priceTextView!!.text = book.price.toString()
    }
}