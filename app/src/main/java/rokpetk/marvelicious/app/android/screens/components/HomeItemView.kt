package rokpetk.marvelicious.app.android.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import rokpetk.marvelicious.app.domain.models.HeroModel

@Composable
fun HomeItemView(
    item: HeroModel,
) {
    Column {
        Text(text = item.name)
    }
}