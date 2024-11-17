package com.nemocompany.movieappworkspace.features.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nemocompany.movieappworkspace.features.feed.presentation.viewmodel.FeedViewModel
import com.nemocompany.movieappworkspace.ui.theme.MovieAppWorkSpaceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireActivity()).apply {
            setContent {
                MovieAppWorkSpaceTheme {
                    Text(text = "FeedFragment")
                }
            }
        }
    }
}