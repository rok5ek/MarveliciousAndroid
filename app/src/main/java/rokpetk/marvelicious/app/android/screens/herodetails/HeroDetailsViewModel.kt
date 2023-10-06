package rokpetk.marvelicious.app.android.screens.herodetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rokpetk.marvelicious.app.android.navigation.Screen
import rokpetk.marvelicious.app.domain.reponses.ApiResponse
import rokpetk.marvelicious.app.domain.usecases.GetHeroDetails
import javax.inject.Inject

@HiltViewModel
class HeroDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getHeroDetails: GetHeroDetails
) : ViewModel() {

    private val _state = MutableStateFlow(HeroDetailsState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<HeroDetailsEvent>()
    val event = _event.asSharedFlow()

    init {
        savedStateHandle.get<String>(Screen.HeroDetails.arg)?.let { id ->
            Log.d("TAG", "app heroDetails id:$id")
            viewModelScope.launch {
                _state.update { it.copy(isLoading = true) }
                getHeroDetails.execute(params = GetHeroDetails.Params(id = id))
                    .collect { response ->
                        Log.d("TAG", "app heroDetails response:$response")
                        when (response) {
                            is ApiResponse.Success -> {
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        name = response.result.name,
                                        image = response.result.image,
                                        comics = response.result.comics,
                                        events = response.result.events,
                                        series = response.result.series,
                                    )
                                }
                            }

                            is ApiResponse.Error -> {
                                _state.update { it.copy(isLoading = false) }
                                response.error?.let {
                                    _event.emit(HeroDetailsEvent.ShowError(message = it.message))
                                }
                            }

                            is ApiResponse.Exception -> {
                                _state.update { it.copy(isLoading = false) }
                                response.error.message?.let {
                                    _event.emit(
                                        HeroDetailsEvent.ShowError(
                                            message = it
                                        )
                                    )
                                }
                            }
                        }
                    }
            }
        }
    }
}