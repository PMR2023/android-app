package fr.ec.app.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.ec.app.R
import fr.ec.app.data.DataProvider
import fr.ec.app.ui.adapter.PostAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = findViewById<RecyclerView>(R.id.list)

        DataProvider.getData(
            onSuccess =  {postList ->
                this@MainActivity.runOnUiThread {
                    list.adapter = PostAdapter(dataSet = postList)
                }

            },
            onError = {error ->
                Log.e("MainActivity","Error: $error")

            }
        )

        list.layoutManager = LinearLayoutManager(this)

    }



}