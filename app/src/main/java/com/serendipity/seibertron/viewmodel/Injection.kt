package com.serendipity.seibertron.viewmodel

import android.content.Context
import com.serendipity.seibertron.model.BookDao
import com.serendipity.seibertron.model.BooksDatabase

object Injection {

    fun provideUserDataSource(context: Context): BookDao {
        val database = BooksDatabase.getInstance(context)
        return database.bookDao()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideUserDataSource(context)
        return ViewModelFactory(dataSource)
    }
}