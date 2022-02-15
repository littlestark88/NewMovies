package id.co.example.mymovie.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.example.mymovie.databinding.FragmentMovieBinding
import id.co.example.mymovie.core.data.core.helper.Constant.EXTRA_DATA
import id.co.example.mymovie.core.data.core.helper.Constant.MOVIE
import id.co.example.mymovie.core.data.core.helper.Constant.TYPE
import id.co.example.mymovie.core.data.source.Resource
import id.co.example.mymovie.core.domain.model.MovieNowPlaying
import id.co.example.mymovie.ui.detail.DetailActivity
import id.co.example.mymovie.ui.viewmodel.MovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var movieNowPlayingAdapter: MovieNowPlayingAdapter
    private var data = mutableListOf<MovieNowPlaying>()
    private var _binding: FragmentMovieBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel.movieNowPlaying.observe(viewLifecycleOwner, { movie ->
            when (movie) {
                is Resource.Loading -> showToast("Loading")
                is Resource.Success -> movieNowPlayingAdapter.setData(movie.data)
                is Resource.Error -> showToast("Error")
            }
        })
        showMovieNowPlaying()
    }

    private fun showMovieNowPlaying() {
        movieNowPlayingAdapter = MovieNowPlayingAdapter(requireActivity(), data) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_DATA, it)
            intent.putExtra(TYPE, MOVIE)
            startActivity(intent)
        }

        binding?.let {
            with(it.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieNowPlayingAdapter
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}