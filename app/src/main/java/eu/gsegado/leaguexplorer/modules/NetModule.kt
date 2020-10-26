package eu.gsegado.leaguexplorer.modules

import eu.gsegado.leaguexplorer.BuildConfig
import eu.gsegado.leaguexplorer.data.remote.SportDbService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * Koin modules about the remote services.
 */
object NetModule {

    val modules = module {

        single { createService(get()) }

        single { createRetrofit(get()) }

        single { createOkHttpClient() }

    }

    private fun createOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
    }

    private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private fun createService(retrofit: Retrofit): SportDbService {
        return retrofit.create(SportDbService::class.java)
    }

}