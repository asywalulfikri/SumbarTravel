package wisata.travel.sumbar.ui.register

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.launch
import wisata.travel.sumbar.base.BaseViewModel
import wisata.travel.sumbar.domain.ResultWrapper
import wisata.travel.sumbar.domain.repository.AuthRepository
import wisata.travel.sumbar.livedata.Event


class RegisterViewModel(private val authRepository: AuthRepository) : BaseViewModel() {

    // Event triggered after successful register
    private val _registerSuccessEvent = MutableLiveData<Event<Unit>>()
    val registerSuccessEvent: LiveData<Event<Unit>> = _registerSuccessEvent

    // Event triggered in response to register failure containing suitable error message
    private val _errorMessageEvent = MutableLiveData<Event<String>>()
    val errorMessageEvent: LiveData<Event<String>> = _errorMessageEvent


    // Status of register button being enabled depending on inputs being valid
    private val _signUpButtonEnabled = MediatorLiveData<Boolean>()
    val signUpButtonEnabled: LiveData<Boolean> = _signUpButtonEnabled

    /**
     * Two-way data binding variables that holds register form inputs
     */

    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")
    val username = MutableLiveData("")

    private val mediatorLiveDataSources = listOf(
        email, password, confirmPassword,username
    )

    init {
        Log.i("info", "Init called")
        mediatorLiveDataSources.forEach {
            _signUpButtonEnabled.addSource(it) { validate() }
        }
    }

    override fun onCleared() {
        Log.i("info","onCleared() called")
        mediatorLiveDataSources.forEach {
            _signUpButtonEnabled.removeSource(it)
        }
        super.onCleared()
    }

    // Validates register form and makes signUpButtonEnabled variable true/false
    private fun validate() {
        _signUpButtonEnabled.value =
            password.value!!.length > 5
                    && password.value!! == confirmPassword.value!!
                    && username.value!!.trim().isNotEmpty()
                    && isEmailValid()
    }

    // Checks if email matches the common pattern
    private fun isEmailValid(): Boolean {
        return !TextUtils.isEmpty(email.value?.trim()) && android.util.Patterns.EMAIL_ADDRESS.matcher(
            email.value!!
        ).matches()
    }

    private fun registerSuccess() {
        _registerSuccessEvent.value = Event(Unit)
    }

    private fun displayErrorMessage(message: String) {
        _errorMessageEvent.value = Event(message)
    }

    fun createAccount() {
        viewModelScope.launch {
            val createAccountTaskResult = authRepository.createAccountWithEmailAndPassword(
                email.value!!,
                password.value!!
            )
            when (createAccountTaskResult) {
                is ResultWrapper.Error -> {
                    when (createAccountTaskResult.error) {
                        is FirebaseAuthWeakPasswordException -> {
                            displayErrorMessage("Password too weak")
                        }
                        is FirebaseAuthUserCollisionException -> {
                            displayErrorMessage("Email is already in use")
                        }
                        is FirebaseNetworkException -> {
                            displayErrorMessage("Network Error, Try again later")
                        }
                    }
                }
                is ResultWrapper.Value -> {
                    registerSuccess()
                }
            }

        }
    }

}