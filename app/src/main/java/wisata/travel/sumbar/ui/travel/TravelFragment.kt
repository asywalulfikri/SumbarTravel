package wisata.travel.sumbar.ui.travel

/**
 * Description: Home Fragment
 * Date: 2019/08/2
 *
 * @author asywalulfikri@gmail.com
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import wisata.travel.sumbar.R

class TravelFragment : Fragment(){




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_travel,
            container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onStop() {
        super.onStop()

    }

    @Override
    override fun onStart() {
        super.onStart()

    }

}
