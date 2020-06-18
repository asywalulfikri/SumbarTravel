package wisata.travel.sumbar.ui.intro.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import wisata.travel.sumbar.ui.intro.IntroSliderFragment.Companion.newInstance

class IntroSliderAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return newInstance(position)
    }

    override fun getCount(): Int {
        return 4
    }
}