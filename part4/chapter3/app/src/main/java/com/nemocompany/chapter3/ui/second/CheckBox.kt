package com.nemocompany.chapter3.ui.second

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// Composable 어노테이션은 뷰가 그려지는 과정,
// Compose에서 상태값을 저장할땐 mutableStateOf를 쓴다
// 다만, mutableStateOf로 상태 값을 저장을해도 다시 그려지게 되면 상태가 사라진다.
@Composable
fun CheckBoxExample() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 상태가 사라지기에 remember로 값을 완전히 저장
        var checked = remember {
            mutableStateOf(false)
        }
        // remember의 값을 가져와야기에 .value로 값을 가져옴
        Checkbox(checked = checked.value,
            onCheckedChange = {
                checked.value = !checked.value
            }
        )
        Text(text = "프로그래머 입니까?")
    }
}

// .value 대신 by로 코드 수를 좀 줄일 수 있음
@Composable
fun CheckBoxExample2() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        var checked by remember {
            mutableStateOf(false)
        }
        Checkbox(checked = checked,
            onCheckedChange = {
                checked = !checked
            }
        )
        Text(text = "프로그래머 입니까?")
    }
}

// 좀 더 간편한 코드로 만들어보기 ( 비구조화, destruction )
@Composable
fun CheckBoxExample3() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        // getter, setter 방식
        val (checked, setChecked) = remember {
            mutableStateOf(false)
        }
        Checkbox(
            checked = checked,
            onCheckedChange = setChecked
        )
        // 텍스트를 클릭했을때도 체크박스 이벤트 처리하기
        Text(text = "프로그래머 입니까?", modifier = Modifier.clickable {
            setChecked(!checked)
        })
    }
}