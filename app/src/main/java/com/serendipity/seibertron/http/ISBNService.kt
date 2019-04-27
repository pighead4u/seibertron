package com.serendipity.seibertron.http

import com.serendipity.seibertron.model.ISBN
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ISBNService {

    @GET("/book/worm/isbn")
    fun searchISBN(@Query("isbn") isbn: String): Flowable<Response<ISBN>>
}