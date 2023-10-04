package rokpetk.marvelicious.app.android.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.serialization.deserializeErrorBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rokpetk.marvelicious.app.data.models.ErrorResponse
import rokpetk.marvelicious.app.domain.models.HeroModel
import rokpetk.marvelicious.app.domain.usecases.GetHeroes
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHeroes: GetHeroes
) : ViewModel() {

    private val searchQueryFlow = MutableStateFlow("")
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<HomeEvent>()
    val event = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            searchQueryFlow
                .debounce {
                    if (it.isEmpty()) {
                        0
                    } else {
                        1000
                    }
                }
                .distinctUntilChanged()
                .map {
                    it.ifEmpty { null }
                }
                .flatMapLatest {
                    getHeroes.execute(
                        params = GetHeroes.Params(nameStartsWith = it)
                    )
                }.collect { response ->
                    onGetHeroResponse(response = response)
                }
        }
    }

    private suspend fun onGetHeroResponse(response: ApiResponse<List<HeroModel>>) {
        when (response) {
            is ApiResponse.Success -> {
                _state.update { it.copy(items = response.data) }
            }

            is ApiResponse.Failure.Error -> {
                val error: ErrorResponse? = response.deserializeErrorBody()
                error?.let { _event.emit(HomeEvent.ShowError(message = it.code)) }
            }

            is ApiResponse.Failure.Exception -> {
                response.message?.let { _event.emit(HomeEvent.ShowError(message = it)) }
            }
        }
    }

    fun onSearchQuery(value: String) {
        _state.update { it.copy(searchQuery = value) }
        searchQueryFlow.tryEmit(value)
    }
}