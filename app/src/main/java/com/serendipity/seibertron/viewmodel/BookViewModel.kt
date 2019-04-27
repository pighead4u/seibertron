package com.serendipity.seibertron.viewmodel

import androidx.lifecycle.ViewModel
import com.serendipity.seibertron.model.Book
import com.serendipity.seibertron.model.BookDao
import io.reactivex.Completable
import io.reactivex.Flowable

class BookViewModel(private val dataSource: BookDao) : ViewModel() {

    /**
     * Get the user name of the user.

     * @return a [Flowable] that will emit every time the user name has been updated.
     */
    // for every emission of the user, get the user name
    fun bookName(isbn: String): Flowable<String> {
        return dataSource.getBookByISBN(isbn)
            .map { book -> book.name }
    }

    /**
     * Update the user name.
     * @param userName the new user name
     * *
     * @return a [Completable] that completes when the user name is updated
     */
    fun updateUserName(book: Book): Completable {
        return dataSource.insertBook(book)
    }

}