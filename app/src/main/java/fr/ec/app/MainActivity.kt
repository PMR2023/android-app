package fr.ec.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val postItems = mutableListOf<Int>()
        val list = findViewById<RecyclerView>(R.id.list)
        repeat(100_000) {item ->
            postItems.add(item)
        }

        list.adapter = PostAdapter(dataSet = postItems)
        list.layoutManager = LinearLayoutManager(this)


    }

    class PostAdapter(
        private val dataSet : List<Int>
    ) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val holder = PostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.post_item,parent,false)
            )

            return holder
        }

        override fun getItemCount(): Int = dataSet.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            (holder as PostViewHolder).bind(dataSet.get(position))
        }

    }

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
}