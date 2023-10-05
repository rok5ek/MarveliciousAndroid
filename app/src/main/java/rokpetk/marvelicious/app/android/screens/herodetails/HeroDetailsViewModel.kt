package rokpetk.marvelicious.app.android.screens.herodetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rokpetk.marvelicious.app.android.navigation.Screen
import rokpetk.marvelicious.app.domain.usecases.GetHero
import javax.inject.Inject

@HiltViewModel
class HeroDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getHero: GetHero
) : ViewModel() {

    init {
        savedStateHandle.get<String>(Screen.HeroDetails.arg)?.let { id ->
            Log.d("TAG", "app heroDetails id:$id")
            viewModelScope.launch {
                getHero.execute(params = GetHero.Params(id = id))
                    .collect {
                        Log.d("TAG", "app heroDetails response:$it")
                    }
            }
        }
    }
}