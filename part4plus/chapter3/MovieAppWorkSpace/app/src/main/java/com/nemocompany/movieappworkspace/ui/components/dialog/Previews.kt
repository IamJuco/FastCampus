package com.nemocompany.movieappworkspace.ui.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nemocompany.movieappworkspace.R
import com.nemocompany.movieappworkspace.ui.models.buttons.LeadingIconData
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogButton
import com.nemocompany.movieappworkspace.ui.theme.MovieAppWorkSpaceTheme

@Preview
@Composable
fun AlertPreview() {
    MovieAppWorkSpaceTheme {
        DialogPopup.Alert(
            title = "Title",
            bodyText = "blah blah blah",
            buttons = listOf(
                DialogButton.UnderlinedText("Okay")
            )
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MovieAppWorkSpaceTheme {
        DialogPopup.Alert(
            title = "Title",
            bodyText = "blah blah blah",
            buttons = listOf(
                DialogButton.Primary("OPEN"),
                DialogButton.SecondaryBorderless("CANCEL")
            )
        )
    }
}

@Preview
@Composable
fun RatingPreview() {
    MovieAppWorkSpaceTheme {
        DialogPopup.Rating(
            movieName = "Title",
            rating = 7.5f,
            buttons = listOf(
                DialogButton.Primary(
                    title = "OPEN",
                    leadingIconData = LeadingIconData(
                        iconDrawable = R.drawable.ic_send,
                        iconContentDescription = null
                    )
                ),
                DialogButton.SecondaryBorderless("CANCEL")
            )
        )
    }
}