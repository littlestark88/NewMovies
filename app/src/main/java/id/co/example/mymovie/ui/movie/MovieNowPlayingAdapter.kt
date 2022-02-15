package id.co.example.mymovie.ui.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.co.example.mymovie.R
import id.co.example.mymovie.core.data.core.helper.Constant.POSTER_URL
import id.co.example.mymovie.core.databinding.ItemListMovieBinding
import id.co.example.mymovie.core.domain.model.MovieNowPlaying

class MovieNowPlayingAdapter(
    private val context: Context,
    private val data: MutableList<MovieNowPlaying>,
    private val onClickListener: (MovieNowPlaying) -> Unit
): RecyclerView.Adapter<MovieNowPlayingAdapter.MovieViewHolder>() {

    fun setData(newListData: List<MovieNowPlaying>?) {
        if(newListData == null) return
        data.clear()
        data.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_movie, parent, false))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        data[position].let { holder.bind(it, onClickListener) }
    }

    override fun getItemCount(): Int = data.size

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(
            data: MovieNowPlaying,
            onClickListener: (MovieNowPlaying) -> Unit
            ) {
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
                itemView.setOnClickListener { onClickListener(data) }
            }
        }
    }
}