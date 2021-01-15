package com.erdemtsynduev.whooshqr.di.modules

import com.erdemtsynduev.whooshqr.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.FieldNamingStrategy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import com.erdemtsynduev.whooshqr.network.CookieInterceptor
import com.erdemtsynduev.whooshqr.utils.ConstApp
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory

import java.lang.reflect.Field
import java.util.*
import java.util.concurrent.TimeUnit

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl(ConstApp.RELEASE_ENDPOINT).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        cookieInterceptor: CookieInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(cookieInterceptor)
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else
                        HttpLoggingInterceptor.Level.NONE
                )
            )
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
    }

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setFieldNamingStrategy(CustomFieldNamingPolicy())
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .serializeNulls()
            .create()
    }

    private class CustomFieldNamingPolicy : FieldNamingStrategy {
        override fun translateName(field: Field): String {
            var name = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field)
            name = name.substring(2, name.length).toLowerCase(Locale.getDefault())
            return name
        }
    }
}