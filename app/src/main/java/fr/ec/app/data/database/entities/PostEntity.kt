package fr.ec.app.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    @PrimaryKey
    val id : String,
    val title :String,
    val subTitle:String,
    val imageUrl : String
)