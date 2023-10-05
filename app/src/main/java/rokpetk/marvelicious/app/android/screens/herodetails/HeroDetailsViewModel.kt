package rokpetk.marvelicious.app.android.screens.herodetails

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import rokpetk.marvelicious.app.domain.usecases.GetHeroes
import javax.inject.Inject

@HiltViewModel
class HeroDetailsViewModel @Inject constructor(
    private val getHeroes: GetHeroes
) : ViewModel() {

}