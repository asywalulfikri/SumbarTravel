package wisata.travel.sumbar.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_register.*
import wisata.travel.sumbar.R
import wisata.travel.sumbar.common.hideKeyboard
import wisata.travel.sumbar.common.showSnack
import wisata.travel.sumbar.livedata.EventObserver
import wisata.travel.sumbar.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val viewModel by viewModel<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_register)

        viewModel.registerSuccessEvent.observe(this, EventObserver {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })

        viewModel.errorMessageEvent.observe(this, EventObserver {
            showSnack(it)
        })


        btnRegister.setOnClickListener {
            hideKeyboard()
            viewModel.createAccount()
        }
    }

}
