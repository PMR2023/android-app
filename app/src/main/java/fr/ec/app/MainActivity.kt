package fr.ec.app

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = findViewById<LinearLayout>(R.id.list)

        repeat(100) {item ->
            val postItem = layoutInflater.inflate(R.layout.post_item,list,false)
            val title  = postItem.findViewById<TextView>(R.id.post_title)
            val subTitle  = postItem.findViewById<TextView>(R.id.post_subTitle)
            val image  = postItem.findViewById<ImageView>(R.id.post_image)
            title.text = "Title $item"
            subTitle.text = "Subtitle $item"

            image.setImageResource(R.mipmap.ic_launcher)


            list.addView(postItem)
        }
    }
}