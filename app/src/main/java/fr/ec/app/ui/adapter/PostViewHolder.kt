package fr.ec.app.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.ec.app.R

class PostViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bind(postItem : Int) {
        val title  = itemView.findViewById<TextView>(R.id.post_title)
        val subTitle  = itemView.findViewById<TextView>(R.id.post_subTitle)
        val image  = itemView.findViewById<ImageView>(R.id.post_image)
        title.text = "Title $postItem"
        subTitle.text = "Subtitle $postItem"
        image.setImageResource(R.mipmap.ic_launcher)

    }

}