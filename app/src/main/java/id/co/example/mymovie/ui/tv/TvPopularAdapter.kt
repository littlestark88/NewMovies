package id.co.example.mymovie.ui.tv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.co.example.mymovie.R
import id.co.example.mymovie.core.data.core.helper.Constant.POSTER_URL
import id.co.example.mymovie.core.databinding.ItemListMovieBinding
import id.co.example.mymovie.core.domain.model.TvPopular

class TvPopularAdapter(
    private val context: Context,
    private val data: MutableList<TvPopular>,
    private val onClickListener: (TvPopular) -> Unit
) : RecyclerView.Adapter<TvPopularAdapter.TvViewHolder>() {

    fun setData(newListData: List<TvPopular>) {
        data.clear()
        data.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) =
        TvViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_list_movie, parent, false)
        )

    override fun onBindViewHolder(holder: TvPopularAdapter.TvViewHolder, position: Int) {
        holder.bind(data[position], onClickListener)
    }

    override fun getItemCount(): Int = data.size

    inner class TvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(
            data: TvPopular,
            onClickListener: (TvPopular) -> Unit
        ) {
            with(binding) {
                tvTitle.text = data.name
                tvDescription.text = data.overview
                tvRating.text = data.voteAverage.toString()
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