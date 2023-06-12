package fr.ec.app.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.ec.app.data.database.entities.PostEntity

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(posts: List<PostEntity>)

    @Query("SELECT * FROM PostEntity")
    suspend fun get(): List<PostEntity>
}