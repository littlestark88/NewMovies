package id.co.example.mymovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.co.example.mymovie.data.source.Resource
import id.co.example.mymovie.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieViewModel.movieNowPlaying.observe(this, { movieNowPlaying ->
            when(movieNowPlaying) {
                is Resource.Loading -> showToast("Loading")
                is Resource.Success -> showToast("Data muncul")
                is Resource.Error -> showToast("Error")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}