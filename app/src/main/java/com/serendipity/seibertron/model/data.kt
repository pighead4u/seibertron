package com.serendipity.seibertron.model

import android.content.Context
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Entity(tableName = "books")
data class Book(
    @PrimaryKey
    val isbn: String,
    val name: String,
    val image: String,
    val author: String,
    val introduction: String,
    val publisher: String,
    val publishingTime: String
)

@Dao
interface BookDao {
    /**
     * Get a user by id.

     * @return the user from the table with a specific id.
     */
    @Query("SELECT * FROM books WHERE isbn = :isbn")
    fun getBookByISBN(isbn: String): Flowable<Book>

    /**
     * Insert a user in the database. If the user already exists, replace it.

     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: Book): Completable

    /**
     * Delete all users.
     */
    @Query("DELETE FROM books")
    fun deleteAllBooks()
}

@Database(entities = arrayOf(Book::class), version = 1)
abstract class BooksDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {

        @Volatile
        private var INSTANCE: BooksDatabase? = null

        fun getInstance(context: Context): BooksDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BooksDatabase::class.java, "Book.db"
            )
                .build()
    }
}