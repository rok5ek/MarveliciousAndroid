package rokpetk.marvelicious.app.android.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rokpetk.marvelicious.app.domain.usecases.GetHeroes
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHeroes: GetHeroes
) : ViewModel() {

    init {
        viewModelScope.launch {
            getHeroes.invoke(GetHeroes.Params(""))
        }
    }
}