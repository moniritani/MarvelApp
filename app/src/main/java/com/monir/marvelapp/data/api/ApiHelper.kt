package com.monir.marvelapp.data.api

import com.monir.marvelapp.BuildConfig
import com.monir.marvelapp.utils.AppConstants
import com.monir.marvelapp.utils.GsonHelper
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InterruptedIOException
import java.net.ProtocolException
import java.net.SocketException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import java.nio.channels.ClosedChannelException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLException

class ApiHelper {
    fun buildMarvelService(): ApiService {
        return buildRetrofit(buildOkHttp())
            .create(ApiService::class.java)
    }

    private fun buildRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonHelper.forSneak()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(client)
            .build()
    }

    private fun buildOkHttp(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        addInterceptors(clientBuilder)
        return finalizeClient(clientBuilder)
    }

    private fun addInterceptors(clientBuilder: OkHttpClient.Builder) {
        addLog(clientBuilder)
    }

    private fun finalizeClient(clientBuilder: OkHttpClient.Builder): OkHttpClient {
        return clientBuilder
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    private fun addLog(builder: OkHttpClient.Builder) {
        if (BuildConfig.DEBUG) {
            val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(logging)
        }
    }

    fun isConnectionException(e: Throwable?): Boolean {
        return e is SocketException || e is ClosedChannelException ||
                e is InterruptedIOException || e is ProtocolException ||
                e is SSLException || e is UnknownHostException ||
                e is UnknownServiceException
    }
}