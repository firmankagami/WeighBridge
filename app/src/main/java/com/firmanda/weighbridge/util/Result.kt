package com.firmanda.weighbridge.util

import java.lang.Exception

sealed class Result<out R> private constructor() {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: Exception) : Result<Nothing>()
}