package com.fastcampus.part5.utils

import android.net.Uri
import android.os.Parcelable
import androidx.navigation.NavHostController
import com.fastcampus.part5.domain.model.Category
import com.fastcampus.part5.domain.model.Product
import com.google.gson.Gson

object NavigationUtil {
    fun navigate(
        controller: NavHostController,
        routeName: String,
        args: Any? = null,
        backStackRouteName: String? = null,
        isLaunchSingleTop: Boolean = true,
        needToRestoreState: Boolean = true
    ) {
        var argument = ""
        if (args != null) {
            when (args) {
                is Parcelable -> {
                    argument = String.format("/%s", Uri.parse(Gson().toJson(args)))
                }

                is Category -> {
                    argument = String.format("/%s", Uri.parse(Gson().toJson(args)))
                }

                is Product -> {
                    argument = String.format("/%s", args.productId)
                }
            }
        }
        controller.navigate(route = "${routeName}$argument") {
            if (backStackRouteName != null) {
                popUpTo(backStackRouteName) {
                    saveState = true
                }
            }
            launchSingleTop = isLaunchSingleTop
            restoreState = needToRestoreState
        }
    }
}