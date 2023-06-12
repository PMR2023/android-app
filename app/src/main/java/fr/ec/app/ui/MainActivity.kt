package fr.ec.app.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
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
import java.lang.Exception
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(
        Dispatchers.Main
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = findViewById<RecyclerView>(R.id.list)
        list.layoutManager = LinearLayoutManager(this)


        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val errorView = findViewById<ConstraintLayout>(R.id.errorView)
        coroutineScope.launch {
            try {
                errorView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                val postList = DataProvider.getPosts()
                list.adapter = PostAdapter(dataSet = postList)
                progressBar.visibility = View.INVISIBLE

            } catch (e: Exception) {
                errorView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                val textError = findViewById<TextView>(R.id.errorTextView)
                textError.text = "Error : ${e.message}"
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}