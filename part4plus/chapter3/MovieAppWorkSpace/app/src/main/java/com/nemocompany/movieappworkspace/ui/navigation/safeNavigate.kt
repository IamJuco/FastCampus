package com.nemocompany.movieappworkspace.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections

// safeNavigate = Stack이 쌓이는것을 적절하게 처리할 수 있게해줌
fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
}