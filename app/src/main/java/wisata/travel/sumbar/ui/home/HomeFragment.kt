package wisata.travel.sumbar.ui.home

/**
 * Description: Home Fragment
 * Date: 2019/08/2
 *
 * @author asywalulfikri@gmail.com
 */

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import wisata.travel.sumbar.R
import wisata.travel.sumbar.library.banner.Banner


class HomeFragment : Fragment() {


    private var banner: Banner<Bean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_home,container, false)

        banner = root.findViewById(R.id.banner)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycle.addObserver(banner!!)

        banner!!.setDefaultGainColor(Color.RED)
        banner!!.setIndicatorGravity(GravityCompat.END)
        banner!!.setIndicatorMargin(15)

        val packs = ArrayList<Bean>()
        packs.add(Bean("https://i1.wp.com/bisnis.nusa7travel.com/wp-content/uploads/2019/08/Nusa7-Travel-Tour-Bali.jpg?resize=660%2C330&ssl=1"))
        packs.add(Bean("https://elhasana.files.wordpress.com/2017/12/banner-bali.jpg"))
        packs.add(Bean("https://cdn.rentalmobilbali.net/wp-content/uploads/2018/08/Paket-Bedugul-Tour-Bali-1-Hari-Tanpa-Hotel-Facebook.jpg"))

        banner!!.setBannerData(packs)
        banner!!.setOnItemClickListener(object : Banner.OnItemClickListener<Bean> {
            override fun onItemClick(position: Int, item: Bean) {
                //Toast.makeText(this@MainActivity, "position = $position", Toast.LENGTH_SHORT).show()
            }
        })
        banner!!.setOnItemBindListener(object : Banner.OnItemBindListener<Bean> {
            override fun onItemBind(position: Int, item: Bean, view: ImageView) {
                activity?.let { Glide.with(it).load(item.url).into(view) }
            }
        })



    }
    private inner class Bean internal constructor(internal var url: String)

    override fun onResume() {
        super.onResume()
        banner!!.setPlaying(true)
    }

    override fun onPause() {
        super.onPause()
        banner!!.setPlaying(false)
    }


    @Override
    override fun onStart() {
        super.onStart()

    }

}
