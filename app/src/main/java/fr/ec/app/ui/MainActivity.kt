package fr.ec.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.ec.app.R
import fr.ec.app.ui.adapter.PostAdapter

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

}