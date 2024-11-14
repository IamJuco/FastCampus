package com.nemocompany.movieappworkspace.ui.models.dialog

import com.nemocompany.movieappworkspace.ui.models.buttons.LeadingIconData

sealed class DialogButton(
    open val title: String,
    open val action: (() -> Unit)?
) {
    data class Primary(
        override val title: String,
        val leadingIconData: LeadingIconData? = null, // Dialog의 Button에 Text 앞에 아이콘을 설정 해주기위함
        override val action: (() -> Unit)? = null
    ) : DialogButton(title, action)

    data class Secondary(
        override val title: String,
        override val action: (() -> Unit)? = null
    ) : DialogButton(title, action)

    data class UnderlinedText(
        override val title: String,
        override val action: (() -> Unit)? = null
    ) : DialogButton(title, action)

    data class SecondaryBorderless(
        override val title: String,
        override val action: (() -> Unit)? = null
    ) : DialogButton(title, action)
}