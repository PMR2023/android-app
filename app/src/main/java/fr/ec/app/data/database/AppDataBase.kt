package fr.ec.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.ec.app.data.database.dao.PostDao
import fr.ec.app.data.database.entities.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun postDao() : PostDao

}