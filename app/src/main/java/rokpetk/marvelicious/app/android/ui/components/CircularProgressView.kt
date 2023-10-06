package rokpetk.marvelicious.app.android.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import rokpetk.marvelicious.app.android.ui.theme.Dimens

@Composable
fun BoxScope.CircularProgressView(
    isVisible: Boolean
) {
    AnimatedVisibility(
        modifier = Modifier.align(Alignment.Center),
        visible = isVisible
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(Dimens.circularProgressSize),
            color = MaterialTheme.colorScheme.primary,
        )
    }
}