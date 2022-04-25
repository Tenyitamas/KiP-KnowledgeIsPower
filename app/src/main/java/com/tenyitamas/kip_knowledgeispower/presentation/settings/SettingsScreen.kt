package com.tenyitamas.kip_knowledgeispower.presentation.settings


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.tenyitamas.kip_knowledgeispower.R
import com.tenyitamas.kip_knowledgeispower.ui.theme.LocalSpacing

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceSmall),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.country_code)
                    .plus(": ")
                    .plus(state.countryCode),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            if (!state.isCountryCodeExpanded) {
                Button(
                    onClick = {
                        viewModel.onEvent(SettingsEvent.CountryCodeChooserClick)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)

                ) {
                    Image(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "arrowDropDownCountryCode"
                    )
                }
            }

            DropdownMenu(
                expanded = state.isCountryCodeExpanded,
                onDismissRequest = {
                    viewModel.onEvent(SettingsEvent.OnCountryCodeDismiss)
                },
                modifier = Modifier
                    .alignByBaseline()
                    .background(MaterialTheme.colors.surface)
            ) {
                SettingsState.countryCodes.forEachIndexed { index, s ->
                    DropdownMenuItem(
                        onClick = {
                            viewModel.onEvent(SettingsEvent.CountryCodeChange(s))
                        }
                    ) {
                        Text(
                            text = s,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
        }

    }
}