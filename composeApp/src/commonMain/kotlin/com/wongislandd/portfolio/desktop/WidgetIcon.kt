package com.wongislandd.portfolio.desktop

import ComicIcon
import ControllerIcon
import DocumentIcon
import EngineeringIcon
import Folder
import GithubIcon
import LinkIcon
import LinkedInIcon
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.portfolio.desktop.data.IconKey
import com.wongislandd.portfolio.desktop.icons.FaceIcon
import com.wongislandd.portfolio.desktop.icons.Palette
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun WidgetIcon(iconKey: IconKey) {
    val iconToUse = when (iconKey) {
        IconKey.PERSON -> {
            FaceIcon
        }

        IconKey.PALETTE -> {
            Palette
        }

        IconKey.FOLDER -> {
            Folder
        }

        IconKey.LINKEDIN -> {
            LinkedInIcon
        }

        IconKey.DOCUMENT -> {
            DocumentIcon
        }

        IconKey.GITHUB -> {
            GithubIcon
        }

        IconKey.COMIC -> {
            ComicIcon
        }

        IconKey.GAME -> {
            ControllerIcon
        }

        IconKey.LINK -> {
            LinkIcon
        }

        IconKey.ENGINEER -> {
            EngineeringIcon
        }

        IconKey.DEFAULT -> {
            Icons.Default.Warning
        }
    }
    Icon(
        iconToUse, contentDescription = "Paint", modifier = Modifier.size(48.dp),
        tint = MaterialTheme.colors.onBackground
    )
}

@Composable
@Preview
fun WidgetIconPreview(@PreviewParameter(IconKeyProvider::class) iconKey: IconKey) {
    WidgetIcon(iconKey)
}

class IconKeyProvider : PreviewParameterProvider<IconKey> {
    override val values: Sequence<IconKey> = IconKey.entries.asSequence()
}
