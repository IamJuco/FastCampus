package com.nemocompany.movieappworkspace

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.nemocompany.movieappworkspace.features.common.viewmodel.ThemeViewModel

open class BaseDialogFragment : DialogFragment() {
    protected val themeViewModel: ThemeViewModel by activityViewModels()
}