package com.nemocompany.chapter3.ui.third

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// SlotApi = 반복되는 값들을 하나로 묶어두기
// 재사용 할 수 있도록
@Composable
fun SlotApiExample() {
    var checked1 = remember {
        mutableStateOf(false)
    }
    var checked2 = remember {
        mutableStateOf(false)
    }
    var checked3 by remember {
        mutableStateOf(false)
    }
    var checked4 by remember {
        mutableStateOf(false)
    }
//    CheckBoxWithText(checked = checked1, "텍스트 1")
//    CheckBoxWithText(checked = checked2, "텍스트 2")

    CheckBoxWithSlot(checked = checked1) {
        Text(text = "텍스트 1", modifier = Modifier.align(Alignment.CenterVertically))
    }
    CheckBoxWithSlot(checked = checked2) {
        Text(text = "텍스트 2")
    }

    CheckBoxWithRealSlot(
        checked = checked3,
        onCheckedChanged = { checked3 = !checked3 }
    ) {
        Text(text = "텍스트 3")
    }
    CheckBoxWithRealSlot(
        checked = checked4,
        onCheckedChanged = { checked4 = !checked4 }
    ) {
        Text(text = "텍스트 4")
    }
}

@Composable
fun CheckBoxWithText(checked: MutableState<Boolean>, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        )
        Text(
            text = text,
            modifier = Modifier.clickable { checked.value = !checked.value }
        )
    }
}

// 람다식으로 만들기 ( 이게 진정한 SlotApi )
@Composable
fun CheckBoxWithSlot(
    checked: MutableState<Boolean>,
    // Compose를 람다식으로 할땐 어노테이션 붙여야함
    // @Composable 뒤에 RowScope를 사용하면 Row에 대한 modifier를 설정 할 수 있음
    // ( SlotApiExample안에 CheckBoxWithSlot의 인자 값에. )
    // 만약 Column 이면 ColumnScope, Box면 BoxScope
    // 이런식으로 Scope 하고 . 을 붙이는것이 SlotApi
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            checked.value = !checked.value
        }
    ) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        )
        content()
    }
}

// 상태를 바꾸는 람다를 @Composable 함수의 인자로 빼고
// 인자에서 MutableState대신 boolean 값으로 변경
// 이렇게 되면 이녀석은 상태를 몰라도됌, 외부에서 상태를 가져옴 ( 아주 좋은 방식인듯 )
@Composable
fun CheckBoxWithRealSlot(
    checked: Boolean,
    onCheckedChanged: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onCheckedChanged()
        }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChanged() }
        )
        content()
    }
}