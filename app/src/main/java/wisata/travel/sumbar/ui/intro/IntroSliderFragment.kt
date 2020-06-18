package wisata.travel.sumbar.ui.intro

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import wisata.travel.sumbar.R

class IntroSliderFragment : Fragment() {
    private var position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            position =
                arguments!!.getInt(ARG_POSITION)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.item_intro, container, false)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        // set page background
        view.background = requireActivity().getDrawable(
            BG_IMAGE[position]
        )
        val title = view.findViewById<TextView>(R.id.tvTitle)
        val titleText = view.findViewById<TextView>(R.id.tvContent)
        val imageView =
            view.findViewById<ImageView>(R.id.imageView)
        // set page title
        title.setText(
            PAGE_TITLES[position]
        )
        // set page sub title text
        titleText.setText(
            PAGE_TEXT[position]
        )
        // set page image
        imageView.setImageResource(
            PAGE_IMAGE[position]
        )
    }

    companion object {
        private const val ARG_POSITION = "slider-position"

        // prepare all title ids arrays
        @StringRes
        private val PAGE_TITLES =
            intArrayOf(R.string.discover, R.string.shop, R.string.offers, R.string.rewards)

        // prepare all subtitle ids arrays
        @StringRes
        private val PAGE_TEXT = intArrayOf(
            R.string.discover_text, R.string.shop_text, R.string.offers_text, R.string.rewards_text
        )

        // prepare all subtitle images arrays
        @StringRes
        private val PAGE_IMAGE = intArrayOf(
            R.drawable.ic_discover, R.drawable.ic_deals, R.drawable.ic_offers, R.drawable.ic_reward
        )

        // prepare all background images arrays
        @StringRes
        private val BG_IMAGE = intArrayOf(
            R.drawable.ic_bg_red, R.drawable.ic_bg_blue, R.drawable.ic_bg_green,
            R.drawable.ic_bg_purple
        )

        /**
         * Use this factory method to create a new instance of
         *
         * @return A new instance of fragment IntroSliderFragment.
         */
        fun newInstance(position: Int): IntroSliderFragment {
            val fragment =
                IntroSliderFragment()
            val args = Bundle()
            args.putInt(
                ARG_POSITION,
                position
            )
            fragment.arguments = args
            return fragment
        }
    }
}