package com.monir.marvelapp.data.api

import com.monir.marvelapp.BuildConfig
import com.monir.marvelapp.utils.AppConstants
import com.monir.marvelapp.utils.GeneralUtils
import com.monir.marvelapp.utils.GsonHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InterruptedIOException
import java.net.ProtocolException
import java.net.SocketException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import java.nio.channels.ClosedChannelException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLException

object ApiHelper {
    fun buildMarvelService(): ApiService {
        return buildRetrofit(buildOkHttp())
            .create(ApiService::class.java)
    }

    private fun buildRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonHelper.forSneak()))
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
        addRequestAuthParams(clientBuilder)
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

    private fun addRequestAuthParams(builder: OkHttpClient.Builder) {
        builder.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val timestamp = (System.currentTimeMillis() / 1000).toString()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(ApiVariables.Parameters.TIME_STAMP, timestamp)
                .addQueryParameter(ApiVariables.Parameters.HASH, generateHash(timestamp))
                .addQueryParameter(ApiVariables.Parameters.API_KEY, AppConstants.API_KEY)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }


    fun isConnectionException(e: Throwable?): Boolean {
        return e is SocketException || e is ClosedChannelException ||
                e is InterruptedIOException || e is ProtocolException ||
                e is SSLException || e is UnknownHostException ||
                e is UnknownServiceException
    }

    private fun generateHash(timeStamp: String): String {
        val formattedString = StringBuilder()
            .append(timeStamp)
            .append(AppConstants.PRIVATE_KEY)
            .append(AppConstants.API_KEY)
            .toString()
        return GeneralUtils.md5(formattedString)
    }
}