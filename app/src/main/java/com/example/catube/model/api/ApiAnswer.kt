package com.example.catube.model.api

sealed class AnswerApi<T>

class PendingAnswerApi<T>: AnswerApi<T>()
class SuccessAnswerApi<T>(val answer: T): AnswerApi<T>()
class ErrorAnswerApi<T>(val exception: Exception): AnswerApi<T>()

fun <T> AnswerApi<T>?.getAnswer(): T? {
    return if (this is SuccessAnswerApi) {
        this.answer
    }
    else {
        null
    }
}
