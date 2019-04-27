package com.serendipity.seibertron.http

data class Response<T>(
    val status: Int,
    val error: String,
    val data: T
)