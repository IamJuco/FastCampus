package com.nemocompany.movieappworkspace.ui.components.dialog

import androidx.compose.runtime.Composable
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogButton
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogContent
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogText
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogTitle

@Composable
fun DialogPopup.Alert(
    title: String,
    bodyText: String,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Header(title),
        dialogContent = DialogContent.Large(
            DialogText.Default(
                bodyText
            )
        ),
        buttons = buttons
    )
}