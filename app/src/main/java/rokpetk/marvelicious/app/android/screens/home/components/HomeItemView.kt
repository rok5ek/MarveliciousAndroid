package rokpetk.marvelicious.app.android.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import rokpetk.marvelicious.app.android.ui.theme.Dimens
import rokpetk.marvelicious.app.domain.models.HeroModel

@Composable
fun HomeItemView(
    item: HeroModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(Dimens.Padding.p12)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        AsyncImage(
            modifier = Modifier
                .height(Dimens.heroImageSize)
                .width(Dimens.heroImageSize)
                .clip(shape = RoundedCornerShape(Dimens.Padding.p10)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.image)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.CenterStart
        )
        Text(text = item.name)
    }
}