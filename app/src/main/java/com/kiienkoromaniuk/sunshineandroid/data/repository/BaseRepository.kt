package com.kiienkoromaniuk.sunshineandroid.data.repository

import com.google.gson.JsonParseException
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.view.extensions.toErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

abstract class BaseRepository {
    protected suspend fun <R> processResponse(
        coroutineContext: CoroutineContext = Dispatchers.IO,
        call: suspend () -> Response<R>,
    ): State<R> = withContext(coroutineContext) {
        try {
            call.invoke().let { response ->
                if (response.isSuccessful) {
                    return@withContext response.body()?.let { responseBody ->
                        State.Success(responseBody)
                    } ?: State.Success(null)
                } else {
                    response.errorBody()?.let { errorBody ->
                        return@withContext try {
                            State.Error(response.code(), errorBody.toErrorResponse())
                        } catch (exception: JsonParseException) {
                            State.Error(response.code(), null, exception)
                        }
                    }
                }
                return@withContext State.Error(response.code(), null)
            }
        } catch (exception: Exception) {
            return@withContext State.Error(0, null, exception)
        }
    }

    protected suspend fun <R> emitResponse(
        call: suspend () -> Response<R>,
    ): Flow<State<R>> {
        return flow {
            emit(State.Progress())
            try {
                call.invoke().let { response ->
                    if (response.isSuccessful) {
                        emit(State.Success(response = response.body()))
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            try {
                                emit(
                                    State.Error(
                                        httpCode = response.code(),
                                        error = errorBody.toErrorResponse(),
                                        exception = null,
                                    ),
                                )
                            } catch (exception: JsonParseException) {
                                emit(
                                    State.Error(
                                        httpCode = response.code(),
                                        error = null,
                                        exception = exception,
                                    ),
                                )
                            }
                        } else {
                            emit(
                                State.Error(
                                    httpCode = response.code(),
                                    error = null,
                                    exception = null,
                                ),
                            )
                        }
                    }
                }
            } catch (exception: Exception) {
                emit(
                    State.Error(
                        httpCode = 0,
                        error = null,
                        exception = exception,
                    ),
                )
            }
        }
    }

    protected suspend fun <R> processBasicResponse(
        call: suspend () -> Response<R>,
    ): R? = withContext(Dispatchers.IO) {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                return@withContext response.body()
            } else {
                return@withContext null
            }
        } catch (exception: Exception) {
            return@withContext null
        }
    }
}
