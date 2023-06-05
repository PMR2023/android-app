package fr.ec.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.ec.app.R
import fr.ec.app.data.Post

class PostAdapter(
    private val dataSet: List<Post>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BIG_POST_ITEM -> {
                PostBigViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.post_item_big, parent, false)
                )
            }

            POST_ITEM -> {
                PostViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)

                )

            }

            else -> {
                throw IllegalArgumentException("Unsupported viewType : $viewType")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            BIG_POST_ITEM
        } else {
            POST_ITEM
        }
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is PostViewHolder -> {
                holder.bind(dataSet[position])
            }
            is PostBigViewHolder -> {
                holder.bind(dataSet[position])
            }
            else -> {
                throw IllegalArgumentException("Unsupported holder : $holder")
            }
        }
    }

    companion object {
        private const val POST_ITEM = 0
        private const val BIG_POST_ITEM = 1
    }

}