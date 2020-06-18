package wisata.travel.sumbar.ui.intro

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_intro.*
import wisata.travel.sumbar.R
import wisata.travel.sumbar.base.BaseActivity
import wisata.travel.sumbar.ui.intro.adapter.IntroSliderAdapter
import wisata.travel.sumbar.ui.register.RegisterActivity
import wisata.travel.sumbar.utils.checkReadExtStoragePermission

class IntroActivity : BaseActivity() {

    private var adapterIntro: IntroSliderAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        adapterIntro = IntroSliderAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )

        // set adapterIntro
        viewPager.adapter = adapterIntro

        // set dot indicators
        tabLayout.setupWithViewPager(viewPager)

        // make status bar transparent

        btnNext.setOnClickListener {
            if (viewPager.currentItem < adapterIntro!!.count) {
                viewPager.currentItem = viewPager.currentItem + 1
            }
        }
        /**
         * Add a listener that will be invoked whenever the page changes
         * or is incrementally scrolled
         */
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (position == adapterIntro!!.count - 1) {
                    btnNext.setText(R.string.get_started)
                    btnNext.setOnClickListener {
                        checkPermission()
                    }
                } else {
                    btnNext.setText(R.string.next)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    fun checkPermission()  {
        if (checkReadExtStoragePermission()) {
            goToMain()
        } else {
            Dexter.withActivity(this)
                .withPermissions(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            goToMain()
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied) {
                            // permission is denied permanently, navigate user to app settings
                            showSettingsDialog()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {
                        token.continuePermissionRequest()
                    }
                })
                .withErrorListener {
                    //TODO change text
                    Toast.makeText(applicationContext, "Error occurred! ", Toast.LENGTH_SHORT).show()
                }
                .onSameThread()
                .check()
        }

    }

    fun goToMain(){
        val intent = Intent(this, RegisterActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        finish()
    }
}