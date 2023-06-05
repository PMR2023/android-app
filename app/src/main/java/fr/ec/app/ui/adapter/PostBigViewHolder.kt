package fr.ec.app.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.ec.app.R

class PostBigViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    fun bind(postItem : Int) {
        val title  = itemView.findViewById<TextView>(R.id.big_post_title)
        val subTitle  = itemView.findViewById<TextView>(R.id.big_post_description)
        val image  = itemView.findViewById<ImageView>(R.id.big_post_image)
        title.text = "Title $postItem"
        subTitle.text = "Subtitle $postItem"
        image.setImageResource(R.mipmap.ic_launcher)

    }
}