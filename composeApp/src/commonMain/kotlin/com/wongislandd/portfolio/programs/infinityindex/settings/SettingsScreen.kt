package com.wongislandd.portfolio.programs.infinityindex.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.GlobalTopAppBar
import com.wongislandd.portfolio.programs.infinityindex.infra.util.sendEvent
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<SettingsViewModel>()
    val screenState by viewModel.settingsScreenStateSlice.screenState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = { GlobalTopAppBar("Settings") }, modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            LazyColumn(
                modifier = Modifier.fillMaxHeight().widthIn(max = 1000.dp).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item {
                    Text(
                        text = "Changing settings may cause a reload of some results",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                }
                items(screenState.toggleSettings.size) { index ->
                    val selectableSetting = screenState.toggleSettings[index]
                    ToggleSetting(
                        selectableToggleSetting = selectableSetting,
                        onSettingSelected = {
                            coroutineScope.sendEvent(
                                viewModel.uiEventBus,
                                SettingsUiEvent.ToggledSetting(
                                    screenState.toggleSettings[index].setting,
                                    selectableSetting.currentValue.not()
                                )
                            )
                        }
                    )
                }
                items(screenState.numberSettings.size) {
                    NumberSetting(
                        numberSetting = screenState.numberSettings[it],
                        onValueChanged = { newValue ->
                            coroutineScope.sendEvent(
                                viewModel.uiEventBus,
                                SettingsUiEvent.NumberSettingChanged(
                                    screenState.numberSettings[it].setting,
                                    newValue
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun NumberSetting(
    numberSetting: AdjustableNumberSetting,
    onValueChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingBase(numberSetting.setting, modifier = modifier) {
        PositiveNumberPicker(
            value = numberSetting.currentValue,
            onValueChange = onValueChanged,
            modifier = Modifier.widthIn(max = 75.dp)
        )
    }
}

@Composable
fun PositiveNumberPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Days"
) {
    var textValue by remember { mutableStateOf(value.toString()) }
    var isError by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        OutlinedTextField(
            value = textValue,
            onValueChange = { newText ->
                if (newText.all { it.isDigit() }) { // Ensure only digits
                    val newValue = newText.toIntOrNull()
                    if (newValue != null && newValue >= 0) {
                        textValue = newText
                        isError = false
                        onValueChange(newValue)
                    } else {
                        textValue = ""
                        isError = true
                    }
                } else {
                    isError = true
                }
            },
            label = { Text(label) },
            isError = isError,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )

        if (isError) {
            Text(
                text = "Invalid",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@Composable
private fun ToggleSetting(
    selectableToggleSetting: SelectableToggleSetting,
    onSettingSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    SettingBase(selectableToggleSetting.setting, modifier = modifier) {
        Switch(
            checked = selectableToggleSetting.currentValue,
            onCheckedChange = { onSettingSelected() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.secondary
            )
        )
    }
}

@Composable
private fun SettingBase(
    setting: Setting<out Any>,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = setting.displayName,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = setting.description,
                style = MaterialTheme.typography.caption
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        content()
    }
}
