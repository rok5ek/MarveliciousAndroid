package rokpetk.marvelicious.app.android.screens.herodetails.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import rokpetk.marvelicious.app.android.ui.theme.Dimens
import rokpetk.marvelicious.app.domain.models.MediaModel

@Composable
fun SectionView(
    isVisible: Boolean,
    title: String,
    items: List<MediaModel>
) {
    AnimatedVisibility(
        visible = isVisible && items.isNotEmpty(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column {
            SectionTitleView(title = title)
            LazyRow(
                contentPadding = PaddingValues(
                    start = Dimens.Padding.p16,
                    end = Dimens.Padding.p16
                ),
                horizontalArrangement = Arrangement.spacedBy(Dimens.Padding.p12)
            ) {
                items(
                    count = items.size,
                    itemContent = { index ->
                        MediaItemView(
                            item = items[index],
                        )
                    }
                )
            }
        }
    }
}