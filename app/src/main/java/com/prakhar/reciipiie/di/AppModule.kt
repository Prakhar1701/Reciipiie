package com.prakhar.reciipiie.di

import com.google.firebase.firestore.FirebaseFirestore
import com.prakhar.reciipiie.network.RecipesAPI
import com.prakhar.reciipiie.repository.FireRepository
import com.prakhar.reciipiie.repository.RecipesRepository
import com.prakhar.reciipiie.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFireBookRepository() = FireRepository(
        favourites = FirebaseFirestore.getInstance().collection("favourites")
    )

    @Singleton
    @Provides
    fun provideRecipesRepository(api: RecipesAPI) = RecipesRepository(api)

    @Singleton
    @Provides
    fun provideRecipesAPI(): RecipesAPI {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(RecipesAPI::class.java)
    }
}
