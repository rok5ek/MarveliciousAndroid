package rokpetk.marvelicious.app.android.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydoves.sandwich.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rokpetk.marvelicious.app.domain.usecases.GetHeroes
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHeroes: GetHeroes
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<HomeEvent>()
    val event = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            Log.d("app", "launch")
            when (val result = getHeroes.execute(GetHeroes.Params(""))) {
                is ApiResponse.Failure.Error -> {
                    Log.d("app", "error:$result")
                }

                is ApiResponse.Failure.Exception -> {
                    Log.d("app", "exception:$result")
                }

                is ApiResponse.Success -> {
                    Log.d("app", "success:$result")
                    _state.update { it.copy(items = result.data) }
                }
            }
        }
    }
}