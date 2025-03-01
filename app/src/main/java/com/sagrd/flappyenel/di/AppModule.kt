package com.sagrd.flappyenel.di

import com.sagrd.flappyenel.data.remote.api.JugadorApi
import com.sagrd.flappyenel.data.repository.JugadorRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideJugadorRepository(jugadorApi: JugadorApi): JugadorRepository {
        return JugadorRepository(jugadorApi)
    }

    @Provides
    @Singleton
    fun provideJugadorApi(moshi: Moshi): JugadorApi {
        return Retrofit.Builder()
            .baseUrl("https://www.flappyenel.somee.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(JugadorApi::class.java)
    }

}
