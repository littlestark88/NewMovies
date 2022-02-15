package id.co.example.mymovie.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import id.co.example.mymovie.core.data.core.helper.Constant.TAB_TITLES
import id.co.example.mymovie.favorite.databinding.FragmentFavoriteBinding
import id.co.example.mymovie.favorite.di.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {


    private var _binding: FragmentFavoriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        val favoritePagerAdapter = FavoritePagerAdapter(requireParentFragment())
        binding.vpFavorite.adapter = favoritePagerAdapter
        TabLayoutMediator(binding.tabs, binding.vpFavorite) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}