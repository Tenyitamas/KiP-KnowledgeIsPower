package com.tenyitamas.kip_knowledgeispower.presentation.detailed.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.ui.theme.LocalSpacing

@Composable
fun ArticleHeader(
    title: String,
    source: String,
    timeString: String,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary
        )

        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))

        Text(
            text = "$source - ${timeString.map {
               if(it.isLetter()) " "
               else it 
            }.joinToString("")}",
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.secondary
        )
    }

}