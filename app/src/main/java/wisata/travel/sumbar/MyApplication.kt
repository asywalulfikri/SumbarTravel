package wisata.travel.sumbar

import android.app.Application
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import wisata.travel.sumbar.koin.firebaseModule
import wisata.travel.sumbar.koin.viewModelsModule

class MyApplication : Application() {
    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
           // Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            androidFileProperties()
            modules(
                listOf(
                    viewModelsModule,
                    firebaseModule
                )
            )
        }

    }
}