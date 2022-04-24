package com.tenyitamas.kip_knowledgeispower.presentation.search.components

import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tenyitamas.kip_knowledgeispower.R
import com.tenyitamas.kip_knowledgeispower.ui.theme.LocalSpacing

@Composable
fun SearchTextField(
    text: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
    hint: String = stringResource(id = R.string.search_hint),
    shouldShowHint: Boolean = false,
    onFocusChanged: (FocusState) -> Unit
) {
    val spacing = LocalSpacing.current
    val focusManager = LocalFocusManager.current
    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                    focusManager.clearFocus()
                    defaultKeyboardAction(ImeAction.Search)
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .padding(2.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(5.dp)
                )
                .background(MaterialTheme.colors.surface.copy(alpha = 0.33f))
                .fillMaxWidth()
                .padding(spacing.spaceMedium)
                .padding(end = spacing.spaceMedium)
                .onFocusChanged {
                    onFocusChanged(it)
                },
            textStyle = TextStyle(
                color = MaterialTheme.colors.onSurface
            ),
            cursorBrush = Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colors.onSurface,
                    MaterialTheme.colors.onSurface
                )
            )

        )
        if(shouldShowHint) {
            Text(
                text = hint,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = spacing.spaceMedium)
            )
            focusManager.clearFocus(true)
        }

        IconButton(
            onClick = onSearch,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_hint),
                tint = MaterialTheme.colors.onSurface
            )
        }
    }

}