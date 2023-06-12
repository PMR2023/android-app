package fr.ec.app.ui

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.ec.app.R
import fr.ec.app.data.DataProvider
import fr.ec.app.ui.adapter.PostAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(
        Dispatchers.Main
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = findViewById<RecyclerView>(R.id.list)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        coroutineScope.launch {
            progressBar.visibility = View.VISIBLE
            val postList = DataProvider.getPosts()
            list.adapter = PostAdapter(dataSet = postList)
            progressBar.visibility = View.INVISIBLE
        }

        list.layoutManager = LinearLayoutManager(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}