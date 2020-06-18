package wisata.travel.sumbar.koin


import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import wisata.travel.sumbar.domain.repository.AuthRepository
import wisata.travel.sumbar.domain.repository.AuthRepositoryImpl
import wisata.travel.sumbar.ui.register.RegisterViewModel

@ExperimentalCoroutinesApi
val viewModelsModule = module {

    //Register
    viewModel { RegisterViewModel(get()) }

    single<AuthRepository> { AuthRepositoryImpl(auth = get()) }


}