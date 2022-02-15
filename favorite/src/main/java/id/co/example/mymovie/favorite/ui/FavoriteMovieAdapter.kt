package id.co.example.mymovie.favorite.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.co.example.mymovie.R
import id.co.example.mymovie.core.data.core.helper.Constant.POSTER_URL
import id.co.example.mymovie.core.databinding.ItemListMovieBinding
import id.co.example.mymovie.core.domain.model.MovieNowPlaying

class FavoriteMovieAdapter: RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>(){

    private var listData = ArrayList<MovieNowPlaying>()
    var onItemClick: ((MovieNowPlaying) -> Unit)? = null

    fun setData(newListData: List<MovieNowPlaying>?) {
        if(newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: MovieNowPlaying) {
            with(binding) {
                tvTitle.text = data.title
                tvDescription.text = data.overview
                tvRating.text = data.voteAverage.toString()
                tvReleaseDate.text = data.releaseDate
                Picasso
                    .get()
                    .load(POSTER_URL + data.posterPath)
                    .placeholder(R.drawable.ic_refresh)
                    .into(imgPoster)
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }
}