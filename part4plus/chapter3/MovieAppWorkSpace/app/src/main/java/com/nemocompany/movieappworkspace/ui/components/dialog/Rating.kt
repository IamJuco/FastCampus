package com.nemocompany.movieappworkspace.ui.components.dialog

import androidx.compose.runtime.Composable
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogButton
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogContent
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogText
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogTitle

@Composable
fun DialogPopup.Rating(
    movieName: String,
    rating: Float,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Large("TITLE"),
        dialogContent = DialogContent.Rating(
            DialogText.Rating(
                text = movieName,
                rating = 8.2f
            )
        ),
        buttons = buttons
    )
}