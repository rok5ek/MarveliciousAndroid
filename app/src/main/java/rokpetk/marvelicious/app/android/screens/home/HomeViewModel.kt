package rokpetk.marvelicious.app.android.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import rokpetk.marvelicious.app.domain.models.HeroModel
import rokpetk.marvelicious.app.domain.reponses.ApiResponse
import rokpetk.marvelicious.app.domain.reponses.ErrorResponse
import rokpetk.marvelicious.app.domain.usecases.GetHeroes
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
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
            _state
                .map {
                    // observe only search query state changes
                    it.searchQuery
                }
                .debounce {
                    if (it.isEmpty()) {
                        // load instantly if the search query is empty
                        0
                    } else {
                        // load 1s after the user has entered search query
                        1000
                    }
                }
                // returns flow where all subsequent repetitions of the same value are filtered out
                .distinctUntilChanged()
                .map {
                    // we should not send empty search query to api, because we can get 409 error
                    it.ifEmpty { null }
                }
                // pre-fetched stale results are not shown to the users in a situation like a network drop.
                // e.g. the search query was “ab,” there is an ongoing network call for “ab,”
                // and the user typed “abc.” Then we’re no longer interested in the outcome of “ab.”
                // flatMapLatest only returns the results of the most recent search query and ignores the rest.
                .flatMapLatest {
                    Log.d("TAG", "app home getHeroes query:$it")
                    _state.update { it.copy(isLoading = true) }
                    getHeroes.execute(params = GetHeroes.Params(nameStartsWith = it))
                }.collect { response ->
                    Log.d("TAG", "app home getHeroes response:$response")
                    onGetHeroResponse(response = response)
                }
        }
    }

    private suspend fun onGetHeroResponse(response: ApiResponse<List<HeroModel>, ErrorResponse>) {
        when (response) {
            is ApiResponse.Success -> {
                _state.update { it.copy(isLoading = false, items = response.result) }
            }

            is ApiResponse.Error -> {
                _state.update { it.copy(isLoading = false) }
                response.error?.let { _event.emit(HomeEvent.ShowError(message = it.message)) }
            }

            is ApiResponse.Exception -> {
                _state.update { it.copy(isLoading = false) }
                response.error.message?.let { _event.emit(HomeEvent.ShowError(message = it)) }
            }
        }
    }

    fun onSearchQuery(value: String) {
        _state.update { it.copy(searchQuery = value) }
    }
}