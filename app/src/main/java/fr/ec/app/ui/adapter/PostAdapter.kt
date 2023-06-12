package fr.ec.app.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.ec.app.R
import fr.ec.app.data.Post
import fr.ec.app.ui.PostViewHolder

class PostAdapter(private val dataSet: List<Post>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // PostViewHolder or PostBigViewHolder
        return when (viewType) {
            POST_ITEM_BIG -> {
                PostBigViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.post_item_big, parent, false)
                )
            }

            POST_ITEM -> {
                PostViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
                )
            } else -> {
                throw IllegalArgumentException("Unsupported viewType : $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.i("PostAdapter", "onBindViewHolder appelé : $position")
        when (holder) {
            is PostViewHolder -> holder.bind(dataSet[position])
            is PostBigViewHolder -> holder.bind(dataSet[position])
            else -> throw IllegalArgumentException("Unsupported holder : $holder")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0) {
            POST_ITEM_BIG
        } else return POST_ITEM
    }

    override fun getItemCount(): Int = dataSet.size

    companion object {
        private const val POST_ITEM = 0
        private const val POST_ITEM_BIG = 1
    }
}