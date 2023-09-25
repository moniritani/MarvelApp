package com.monir.marvelapp.base

sealed class ErrorType {
    data object NetworkError : ErrorType()
    data object UnknownError : ErrorType()
    data class ApiError(val code: Int, val apiMessage: String) : ErrorType()
}