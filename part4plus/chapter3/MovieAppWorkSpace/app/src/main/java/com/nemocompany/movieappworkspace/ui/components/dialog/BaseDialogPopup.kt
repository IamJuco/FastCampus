package com.nemocompany.movieappworkspace.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.nemocompany.movieappworkspace.ui.components.dialog.components.button.DialogButtonsColumn
import com.nemocompany.movieappworkspace.ui.components.dialog.components.content.DialogContentWrapper
import com.nemocompany.movieappworkspace.ui.components.dialog.components.title.DialogTitleWrapper
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogButton
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogTitle
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogContent
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogText
import com.nemocompany.movieappworkspace.ui.theme.MovieAppWorkSpaceTheme
import com.nemocompany.movieappworkspace.ui.theme.Paddings

@Composable
fun BaseDialogPopup(
    dialogTitle: DialogTitle? = null,
    dialogContent: DialogContent? = null,
    buttons: List<DialogButton>? = null
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(Paddings.none),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            dialogTitle?.let {
                DialogTitleWrapper(it)
            }
            Column(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .padding(
                        start = Paddings.xlarge,
                        end = Paddings.xlarge,
                        bottom = Paddings.xlarge
                    )
            ) {
                dialogContent?.let { DialogContentWrapper(it) }
                buttons?.let { DialogButtonsColumn(it) }
            }
        }
    }
}

@Preview
@Composable
fun BaseDialogPopupPreview() {
    MovieAppWorkSpaceTheme {
        BaseDialogPopup(
            dialogTitle = DialogTitle.Large("TITLE"),
            dialogContent = DialogContent.Default(
                DialogText.Default("abced abced abced abced abced abced abced abced abced abced abced abced abced abced abced")
            ),
            buttons = listOf(
                DialogButton.Secondary("Okay"),
                DialogButton.UnderlinedText("Cancel")
            )
        )
    }
}

@Preview
@Composable
fun BaseDialogPopupPreview2() {
    MovieAppWorkSpaceTheme {
        BaseDialogPopup(
            dialogTitle = DialogTitle.Large("TITLE"),
            dialogContent = DialogContent.Rating(
                DialogText.Rating(
                    text = "Jurassic Park",
                    rating = 8.2f
                )
            ),
            buttons = listOf(
                DialogButton.Primary("Okay"),
                DialogButton.Secondary("Cancel")
            )
        )
    }
}