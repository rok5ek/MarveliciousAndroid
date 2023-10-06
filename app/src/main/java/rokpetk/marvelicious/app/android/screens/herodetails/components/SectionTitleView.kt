package rokpetk.marvelicious.app.android.screens.herodetails.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import rokpetk.marvelicious.app.android.ui.theme.Dimens

@Composable
fun SectionTitleView(
    title: String
) {
    Text(
        title,
        modifier = Modifier.padding(
            start = Dimens.Padding.p16,
            end = Dimens.Padding.p16,
            bottom = Dimens.Padding.p8,
        )
    )
}