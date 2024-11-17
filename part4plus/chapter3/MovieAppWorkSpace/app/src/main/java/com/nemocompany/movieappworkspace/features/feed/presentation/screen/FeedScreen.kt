package com.nemocompany.movieappworkspace.features.feed.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nemocompany.movieappworkspace.R
import com.nemocompany.movieappworkspace.features.feed.presentation.input.IFeedViewModelInput
import com.nemocompany.movieappworkspace.features.feed.presentation.output.FeedState
import com.nemocompany.movieappworkspace.ui.components.movie.CategoryRow
import com.nemocompany.movieappworkspace.ui.theme.Paddings
import timber.log.Timber

val COMMON_HORIZONTAL_PADDING = Paddings.medium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    feedStateHolder: State<FeedState>,
    input: IFeedViewModelInput,
    buttonColor: State<Color>,
    changeAppColor: () -> Unit
) {
    val btnColor by remember { buttonColor }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .requiredHeight(70.dp)
                    // TopAppBar의 elevation이 사라졌으므로 shadow로 대체
                    .shadow(0.dp),
                title = {
                    Text(
                        modifier = Modifier
                            .padding(
                                start = COMMON_HORIZONTAL_PADDING,
                            )
                            // 강의와다름, Text가 중앙,왼쪽에 정렬이 안되어있어서 수정
                            .fillMaxHeight() // TopBar의 전체 높이만큼 채움
                            .wrapContentHeight(Alignment.CenterVertically), // 수직 중앙 정렬
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.displaySmall
                    )
                },
                actions = {
                    AppBarMenu(
                        btnColor = btnColor,
                        changeAppColor = changeAppColor,
                        input = input
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        BodyContent(
            // 최신버전에선 innerPadding을 설정해줘야 해서 넣었음
            modifier = Modifier.padding(innerPadding),
            feedState = feedStateHolder.value,
            input = input
        )
    }
}

@Composable
fun AppBarMenu(
    btnColor: Color,
    changeAppColor: () -> Unit,
    input: IFeedViewModelInput
) {
    Row(
        modifier = Modifier
            .padding(
                end = COMMON_HORIZONTAL_PADDING
            )
            // 강의와다름 아이콘이 중앙에 안왔음
            .fillMaxHeight(), // AppBar 높이에 맞춤
        verticalAlignment = Alignment.CenterVertically // 수직 중앙 정렬
    ) {
        IconButton(
            onClick = {
                changeAppColor()
            }
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = btnColor)
            )
        }

        IconButton(
            onClick = {
                input.openInfoDialog()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_information),
                contentDescription = "Information",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun BodyContent(
    modifier: Modifier = Modifier,
    feedState: FeedState,
    input: IFeedViewModelInput
) {
    when (feedState) {
        is FeedState.Loading -> {
            Timber.d("MoviesScreen: Loading")
            Box(
                modifier = modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = modifier.align(Alignment.Center)
                )
            }
        }

        is FeedState.Main -> {
            Timber.d("MoviesScreen: Success")
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                itemsIndexed(feedState.categories) { _, category ->
                    CategoryRow(
                        categoryEntity = category,
                        input = input
                    )
                }
            }
        }

        is FeedState.Failed -> {
            Timber.d("MoviesScreen: Error")
            RetryMessage(
                message = feedState.reason,
                input = input
            )
        }
    }
}

val IMAGE_SIZE = 48.dp

@Composable
fun RetryMessage(
    modifier: Modifier = Modifier,
    message: String,
    input: IFeedViewModelInput
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .requiredSize(IMAGE_SIZE),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_warning),
            contentDescription = null
        )

        Text(
            text = message,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                top = Paddings.xlarge,
                bottom = Paddings.extra
            )
        )

        Button(
            onClick = { input.refreshFeed() }
        ) {
            Text("RETRY")
        }
    }
}