package com.tenyitamas.kip_knowledgeispower.presentation.detailed.components

import android.os.Build
import android.text.Html
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tenyitamas.kip_knowledgeispower.ui.theme.LocalSpacing

@Composable
fun ArticleHeader(
    title: String,
    description: String,
    source: String,
    timeString: String,
    onShare: () -> Unit,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceMedium)
                .padding(vertical = spacing.spaceMedium)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(end = spacing.spaceMedium)
            )

            Spacer(modifier = Modifier.height(spacing.spaceSmall))

            Text(
                text = description,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primary.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))

            val sourceDisplayText = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml("<div>$source</div>", Html.FROM_HTML_MODE_COMPACT).toString()
            } else {
                source
            }

            val timeDisplayString = timeString.map {
                if (it.isLetter()) " "
                else it
            }.joinToString("")

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            Text(
                text = "$sourceDisplayText - $timeDisplayString",
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.secondary
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

        }

        IconButton(
            onClick = { onShare() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = spacing.spaceMedium)
                .padding(top = spacing.spaceMedium)
                .size(spacing.spaceLarge)

        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "share",
                tint = MaterialTheme.colors.secondary
            )
        }



    }

}