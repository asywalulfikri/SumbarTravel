package wisata.travel.sumbar.ui.main

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.rl_searchView
import kotlinx.android.synthetic.main.toolbar_search.*
import wisata.travel.sumbar.R
import wisata.travel.sumbar.base.BaseActivity
import wisata.travel.sumbar.ui.account.AccountFragment
import wisata.travel.sumbar.ui.home.HomeFragment
import wisata.travel.sumbar.ui.news.ArticlesFragment
import wisata.travel.sumbar.ui.travel.TravelFragment
import wisata.travel.sumbar.utils.ViewPagerAdapter

class MainActivity :  BaseActivity(){

    internal var prevMenuItem: MenuItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewPager.offscreenPageLimit = 3

        bottom_navigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_menu -> {
                    rl_toolbar.visibility = View.VISIBLE
                    viewPager.currentItem = 0
                }

                R.id.travel_menu -> {
                    rl_toolbar.visibility = View.VISIBLE
                    viewPager.currentItem = 1
                }
                R.id.articles_menu -> {
                    rl_toolbar.visibility = View.VISIBLE
                    rl_searchView.visibility = View.VISIBLE
                    viewPager.currentItem = 2
                }
                R.id.account_menu -> {
                    rl_toolbar.visibility = View.VISIBLE
                    viewPager.currentItem = 3
                }
            }
            false
        }

        // viewPager.disableScroll(true)
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position:Int, positionOffset:Float, positionOffsetPixels:Int) {

            }

            override fun onPageSelected(position:Int) {
                if (prevMenuItem != null)
                {
                    prevMenuItem!!.isChecked = false
                }
                else
                {
                    bottom_navigation.menu.getItem(0).isChecked = false
                }
                Log.d("page", "onPageSelected: $position")
                bottom_navigation.menu.getItem(position).isChecked = true
                prevMenuItem = bottom_navigation.menu.getItem(position)

            }

            override fun onPageScrollStateChanged(state:Int) {

            }
        })

        setupViewPager(viewPager)

        etSearchView.setOnEditorActionListener(TextView.OnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (etSearchView.text.toString() !== "") {
                    val text: String = etSearchView.text.toString().toLowerCase()
                    val text1 = text.split(" ").toTypedArray()
                    hideKeyboard()
                    //val intent = Intent(activity, SearcListProductActivity::class.java)
                    // intent.putExtra("keyword", text1[0])
                    // startActivity(intent)
                }
            }
            false
        })

    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        val homeFragment = HomeFragment()
        val travelFragment = TravelFragment()
        val articlesFragment = ArticlesFragment()
        val accountFragment = AccountFragment()
        adapter.addFragment(homeFragment)
        adapter.addFragment(travelFragment)
        adapter.addFragment(articlesFragment)
        adapter.addFragment(accountFragment)

        viewPager.adapter = adapter
    }

}
