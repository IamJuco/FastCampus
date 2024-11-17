package com.nemocompany.movieappworkspace

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.nemocompany.movieappworkspace.features.common.viewmodel.ThemeViewModel

// 이런식으로 BaseFragment를 theme값을 가져와 쓸 수 있도록 만듬
open class BaseFragment : Fragment() {
    protected val themeViewModel: ThemeViewModel by activityViewModels()
}