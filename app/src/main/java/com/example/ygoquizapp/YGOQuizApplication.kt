package com.example.ygoquizapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


//val koinModule = {
//        single{Room.databaseBuilder(androidContext(), CardDataDatabase::class.java, "card_data").build()}
//        factory { get<CardDataDatabase>().cardDataDao() }
//    }

@HiltAndroidApp
class YGOQuizApplication: Application() {
//     override fun onCreate() {
//         super.onCreate()
//         startKoin{}
//     }
 }
