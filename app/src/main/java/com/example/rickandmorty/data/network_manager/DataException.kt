package com.example.rickandmorty.data.network_manager

class DataException(
    val errorCode: String,
    val errorType: DataErrorType
) : Exception()