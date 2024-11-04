package com.nemocompany.chapter4.ui.third

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun DropDownExample() {
    var expandDropDownMenu by remember {
        mutableStateOf(false)
    }
    var counter by remember {
        mutableIntStateOf(0)
    }

    Column {
        Button(onClick = { expandDropDownMenu = true }) {
            Text(text = "드롭다운 메뉴 열기")
        }
        Text(text = "카운터: $counter")
    }

    DropdownMenu(expanded = expandDropDownMenu, onDismissRequest = {
        expandDropDownMenu = false
    }) {
        DropdownMenuItem(text = { Text(text = "증가") }, onClick = { counter++ })
        DropdownMenuItem(text = { Text(text = "감소") }, onClick = { counter-- })
    }
}