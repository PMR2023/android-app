package fr.ec.app.data

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.google.gson.Gson
import fr.ec.app.data.api.Services
import fr.ec.app.data.api.response.PostResponse
import fr.ec.app.data.api.response.PostsResponse
import fr.ec.app.data.database.AppDataBase
import fr.ec.app.data.database.entities.PostEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors


class DataProvider(private val application: Application) {


    private val appDatabase: AppDataBase =
        Room.databaseBuilder(application, AppDataBase::class.java, "app-database")
            .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/PMR2023/android-app/main/data/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val services = retrofit.create(Services::class.java)


    suspend fun getPosts(): List<Post> = withContext(Dispatchers.Default) {
        try {
            val postsResponse = services.getPosts()

            appDatabase.postDao().add(postsResponse.posts.map {
                PostEntity(
                    id = it.id,
                    title = it.name.orEmpty(),
                    subTitle = it.tagline.orEmpty(),
                    imageUrl = it.thumbnail?.url ?: ""
                )
            })


        }catch (e :Exception) {
            Log.e("DataProvider","error : ${e.message}")
        }
        val savedPosts = appDatabase.postDao().getPosts()

        val postList =
            savedPosts.map {
                    Post(
                        title = it.title,
                        subTitle = it.subTitle,
                        imageUrl = it.imageUrl
                    )
                }

        postList

    }


}