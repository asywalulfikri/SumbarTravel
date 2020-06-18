package wisata.travel.sumbar.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import wisata.travel.sumbar.R
import wisata.travel.sumbar.ui.intro.IntroActivity
import kotlin.system.exitProcess

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({

            startActivity(Intent(this, IntroActivity::class.java))
            finish()

        }, 2000)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        exitProcess(1);
    }
}
