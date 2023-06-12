package fr.ec.app.data

import android.accounts.NetworkErrorException
import android.app.Application
import android.util.Log
import androidx.room.Room
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import fr.ec.app.data.api.Services
import fr.ec.app.data.database.AppDataBase
import fr.ec.app.data.database.entities.PostEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.BufferedReader
import java.net.HttpURLConnection

class DataProvider(private val application: Application) {
    private val POST_API_URL = "https://raw.githubusercontent.com/PMR2023/android-app/main/data/"

    private val database = Room.databaseBuilder(application, AppDataBase::class.java, "app-database")
        .build()

    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(POST_API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val services = retrofit.create(Services::class.java)

    suspend fun getPosts() =
        withContext(Dispatchers.IO) {
            try {
                // Get posts by Retrofit (internet)
                val postsResponse = services.getPosts()
                // Save them in our database
                database.postDao().add(
                postsResponse.posts.map {
                    PostEntity(
                        id = it.id.toInt(),
                        title = it.name,
                        subTitle = it.tagline,
                        imageUrl = it.thumbnail?.url ?: ""
                    )
                })
            } catch (e: Exception) {
                Log.e("DataProvider", "Error: ${e.message}")
            }

            val savedPosts = database.postDao().get()

            savedPosts.map {
                Post(
                    title = it.title,
                    subTitle = it.subTitle,
                    imageURI = it.imageUrl
                )
            }
        }
}