package id.co.example.mymovie.ui.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.example.mymovie.databinding.FragmentTvPopularBinding
import id.co.example.mymovie.core.data.core.helper.Constant.EXTRA_DATA
import id.co.example.mymovie.core.data.core.helper.Constant.TV
import id.co.example.mymovie.core.data.core.helper.Constant.TYPE
import id.co.example.mymovie.core.data.source.Resource
import id.co.example.mymovie.core.domain.model.TvPopular
import id.co.example.mymovie.ui.detail.DetailActivity
import id.co.example.mymovie.ui.viewmodel.MovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class TvPopularFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var tvPopularAdapter : TvPopularAdapter
    private var data = mutableListOf<TvPopular>()
    private var _binding: FragmentTvPopularBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvPopularBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel.tvPopular.observe(viewLifecycleOwner, { tv ->
                when (tv) {
                    is Resource.Loading -> showToast("Loading")
                    is Resource.Success -> tv.data?.let { tvPopularAdapter.setData(it) }
                    is Resource.Error -> showToast("Error")
                }
        })
        showTvPopular()
    }

    private fun showTvPopular() {
        tvPopularAdapter = TvPopularAdapter(requireActivity(), data) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_DATA, it)
            intent.putExtra(TYPE, TV)
            startActivity(intent)
        }
        binding?.let {
            with(it.rvTvPopular) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvPopularAdapter
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