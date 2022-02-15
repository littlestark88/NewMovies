package id.co.example.mymovie.favorite.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FavoritePagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFavoriteFragment(true)
            1 -> MovieFavoriteFragment(false)
            else -> Fragment()
        }
    }
}