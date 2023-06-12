package fr.ec.app.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.ec.app.R
import fr.ec.app.data.Post

class PostBigViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    fun bind(postItem : Post) {
        val title  = itemView.findViewById<TextView>(R.id.big_post_title)
        val subTitle  = itemView.findViewById<TextView>(R.id.big_post_description)
        val image  = itemView.findViewById<ImageView>(R.id.big_post_image)
        title.text = postItem.title
        subTitle.text = postItem.subTitle
        Picasso.get().load(postItem.imageUrl).into(
            image
        )

    }
}