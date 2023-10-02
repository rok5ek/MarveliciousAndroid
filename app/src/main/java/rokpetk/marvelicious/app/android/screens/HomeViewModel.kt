package rokpetk.marvelicious.app.android.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.serialization.deserializeErrorBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rokpetk.marvelicious.app.data.models.ErrorResponse
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
            when (val result = getHeroes.execute(GetHeroes.Params(""))) {
                is ApiResponse.Success -> {
                    _state.update { it.copy(items = result.data) }
                }

                is ApiResponse.Failure.Error -> {
                    val error: ErrorResponse? = result.deserializeErrorBody()
                    error?.let { _event.emit(HomeEvent.ShowError(message = it.code)) }
                }

                is ApiResponse.Failure.Exception -> {
                    result.message?.let { _event.emit(HomeEvent.ShowError(message = it)) }
                }
            }
        }
    }
}