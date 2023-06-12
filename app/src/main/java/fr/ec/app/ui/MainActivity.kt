package fr.ec.app.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import fr.ec.app.R
import fr.ec.app.data.DataProvider
import fr.ec.app.ui.adapter.PostAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = findViewById<RecyclerView>(R.id.list)
        val buttonRetry = findViewById<Button>(R.id.buttonRetry)
        val loadingBar = findViewById<ProgressBar>(R.id.loadingProgressBar)
        val errorText = findViewById<TextView>(R.id.textError)

        val dataProvider = DataProvider(application)

        buttonRetry.setOnClickListener {
            getList(dataProvider, list, loadingBar, errorText, buttonRetry)
        }

        getList(dataProvider, list, loadingBar, errorText, buttonRetry)

        list.layoutManager = GridLayoutManager(this, 2).apply {
            spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == 0) {
                        2
                    } else 1
                }

            }
        }
    }
    private fun getList(
        dataProvider: DataProvider,
        list: RecyclerView,
        loadingBar: ProgressBar,
        errorText: TextView,
        bRetry: Button
    ) {
        coroutineScope.launch {
            errorText.visibility = ImageView.GONE
            bRetry.visibility = ImageView.GONE
            loadingBar.visibility = ImageView.VISIBLE
            try {
                val postList = dataProvider.getPosts()
                list.adapter = PostAdapter(dataSet = postList)
                loadingBar.visibility = ImageView.GONE
            } catch (e: Exception) {
                loadingBar.visibility = ImageView.GONE
                errorText.visibility = ImageView.VISIBLE
                errorText.text = "Error: ${e.message}"
                bRetry.visibility = ImageView.VISIBLE
            }
        }
    }

}