package com.wongislandd.portfolio.programs.infinityindex.infra

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.portfolio.programs.infinityindex.infra.composables.TopLevelEntityDetails
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityModel

@Composable
fun EntityDetails(
    entity: EntityModel,
    modifier: Modifier = Modifier,
    mainContent: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopLevelEntityDetails(entity, modifier)
        mainContent()
    }
}