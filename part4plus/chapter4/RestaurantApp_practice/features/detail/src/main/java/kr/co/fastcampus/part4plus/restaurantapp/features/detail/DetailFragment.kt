package kr.co.fastcampus.part4plus.restaurantapp.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.fastcampus.part4plus.restaurantapp.core.BaseFragment
import kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation.RestaurantDetailScreen
import kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation.output.DetailUiEffect
import kr.co.fastcampus.part4plus.restaurantapp.features.detail.presentation.viewmodel.RestaurantDetailViewModel
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.navigation.safeNavigate
import kr.co.fastcampus.part4plus.restaurantapp.ui_components.theme.RestaurantAppTheme

@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    private val viewModel: RestaurantDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        observeUiEffects()
        init()
        return ComposeView(requireContext()).apply {
            setContent {
                RestaurantAppTheme(
                    themeState = themeViewModel.themeState.collectAsState()
                ) {
                    RestaurantDetailScreen(
                        restaurantDetailState = viewModel.outputs.detailState.collectAsState(),
                        input = viewModel.inputs
                    )
                }
            }
        }
    }

    private fun init() {
        val id = arguments?.getInt("id") ?: 0
        lifecycleScope.launch {
            viewModel.initDetail(id)
        }
    }

    private fun observeUiEffects() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.outputs.detailUiEffect.collectLatest {
                    when (it) {
                        is DetailUiEffect.Back -> {
                            findNavController().safeNavigate(
                                "App://Feed"
                            )
                        }

                        is DetailUiEffect.OpenUrl -> {
                            findNavController().safeNavigate(
                                "App://Map/${it.url}"
                            )
                        }

                        is DetailUiEffect.RateRestaurant -> {
                            findNavController().safeNavigate(
                                "App://Rating/${it.restaurantName}/${it.rating}"
                            )
                        }
                    }
                }
            }
        }
    }
}
