package iammert.com.androidarchitecture.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class MovieViewModelFactory @Inject
constructor(private val creators: Map<Class<out ViewModel>, Provider<out ViewModel>>)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
            try {
                (creators[modelClass]
                        ?: creators.filterKeys { modelClass.isAssignableFrom(it) }.values.firstOrNull()
                        ?: throw IllegalArgumentException("unknown model class " + modelClass))
                        .get() as T
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
}
