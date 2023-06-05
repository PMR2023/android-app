package fr.ec.app.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.ec.app.R
import fr.ec.app.data.Post

class PostViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bind(postItem : Post) {
        val title  = itemView.findViewById<TextView>(R.id.post_title)
        val subTitle  = itemView.findViewById<TextView>(R.id.post_subTitle)
        val image  = itemView.findViewById<ImageView>(R.id.post_image)
        title.text = postItem.title
        subTitle.text = postItem.subTitle
        image.setImageResource(R.mipmap.ic_launcher)

    }

}