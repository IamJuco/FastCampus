package com.nemocompany.movieappworkspace.ui.components.dialog.components.content

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.em
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogContent
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogText
import com.nemocompany.movieappworkspace.ui.theme.Paddings

data class DialogContentStyle(
    val textStyle: @Composable () -> TextStyle = { MaterialTheme.typography.bodyLarge },
    val contentTopPadding: Dp = Paddings.xlarge,
    val contentBottomPadding: Dp = Paddings.extra
)

val LocalDialogContentStyle = compositionLocalOf { DialogContentStyle() }

@Composable
fun ColumnScope.DialogContentWrapper(content: DialogContent) {
    when (content) {
        is DialogContent.Default -> {
            CompositionLocalProvider(
                LocalDialogContentStyle provides DialogContentStyle(
                    textStyle = {
                        MaterialTheme.typography.bodyMedium.copy(
                            // em = 기본 텍스트 크기를 기준 1.6배로 설정
                            lineHeight = 1.6.em
                        )
                    },
                    contentTopPadding = Paddings.small,
                    contentBottomPadding = Paddings.extra
                )
            ) {
                NormalTextContent(content.dialogText)
            }
        }

        is DialogContent.Large -> {
            CompositionLocalProvider(
                LocalDialogContentStyle provides DialogContentStyle(
                    textStyle = {
                        MaterialTheme.typography.titleLarge.copy(
                            // em = 기본 Text 크기(sp)를 기준 Text줄 높이를 1.6배로 설정
                            // 만약 16sp면 16 x 1.6 = 25.6sp
                            lineHeight = 1.6.em
                        )
                    },
                    contentTopPadding = Paddings.extra,
                    contentBottomPadding = Paddings.extra
                )
            ) {
                NormalTextContent(content.dialogText)
            }
        }

        is DialogContent.Rating -> {
            RatingContent(content.dialogText)
        }
    }
}

// CenterHorizontally를 하기위해 ColumnScope 확장함수를 사용
// ColumnScope를 가져다 쓰기위해 Column인곳에다가 가져다 써야함
// 만약 Column이 아닌곳에 쓴다면 그 함수역시 ColumnScope를 붙여야함
@Composable
fun ColumnScope.NormalTextContent(text: DialogText.Default) {
    Text(
        text = getStringFromDialogText(text),
        modifier = Modifier
            .padding(
                top = LocalDialogContentStyle.current.contentTopPadding,
                bottom = LocalDialogContentStyle.current.contentBottomPadding
            )
            .align(Alignment.CenterHorizontally),
        textAlign = TextAlign.Center,
        // invoke 대신 textStyle()를 해도 되지만 람다 함수를 호출했다는걸 명시적으로 보여주기위해 invoke를 사용
        style = LocalDialogContentStyle.current.textStyle.invoke()
    )
}

// DialogText.Default? 의 id 값에 따라 분기처리 하기위해서 만듦
// 즉, null일때 아닐때 처리해주려고
@Composable
fun getStringFromDialogText(text: DialogText.Default): String =
    text.id?.let {
        stringResource(id = it)
    } ?: text.text ?: ""