package com.example.ygoquizapp.module

import android.content.Context
import com.example.ygoquizapp.db.CardDataDao
import com.example.ygoquizapp.db.CardDataDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.room.Room
import com.example.ygoquizapp.model.CardDataInitialize

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Singleton
    @Provides
    fun provideCardDataDatabase(
        @ApplicationContext context : Context
    ): CardDataDatabase {
        return Room.databaseBuilder(
            context,
            CardDataDatabase::class.java,
            "card_data"
        ).build()
    }

    @Singleton
    @Provides
    fun provideCardDataDao(db: CardDataDatabase): CardDataDao{
        return db.cardDataDao()
    }

//    @Singleton
//    @Provides
//    fun provideCardDataInitialize(dao:CardDataDao): CardDataInitialize{
//        return CardDataInitialize(dao)
//    }
}