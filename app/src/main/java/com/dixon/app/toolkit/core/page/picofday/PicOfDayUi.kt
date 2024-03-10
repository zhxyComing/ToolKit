package com.dixon.app.toolkit.core.page.picofday

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.dixon.app.toolkit.core.util.Ln
import com.dixon.app.toolkit.logic.model.PicInfo

/**
 * 翻译页面布局文件
 */
@Composable
fun PicOfDayUi(
    picOfDayResult: Result<List<PicInfo>>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PicOfDayTitleShow()
        PicOfDayContentShow(picOfDayResult = picOfDayResult)
    }
}

@Composable
fun PicOfDayTitleShow(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 30.dp,
                start = 20.dp,
                bottom = 20.dp
            ),
        text = "每日一图",
        color = Color.White,
        fontSize = 30.sp
    )
}

@Composable
fun PicOfDayContentShow(picOfDayResult: Result<List<PicInfo>>, modifier: Modifier = Modifier) {
    if (picOfDayResult.isSuccess) {
        picOfDayResult.getOrNull()?.let { res ->
            // 首次加载数据是空的 or 请求成功数据是空的
            if (res.isNotEmpty()) {
                PicOfDayOneShow(res[0])
//                Ln.i("PicOfDay", "图片加载重组 - 根布局")
                // ⭐️ 卡顿是LazyColumn自身的问题，就算纯文字列表都卡..
                // （但是有图后确实更卡了，按理说图压缩过了，最神奇的是放一会就不卡了）
//                LazyColumn(
//                    modifier = modifier.padding(horizontal = 20.dp),
//                ) {
//                    items(res, key = { it.url }) { picInfo ->
//                        Ln.i("PicOfDay", "图片加载重组 - item $picInfo")
//                        PicOfDayItemShow(picInfo = picInfo)
//                    }
//                }
            }
        }
    }
}

/**
 * 只显示一张图
 */
@Composable
fun PicOfDayOneShow(picInfo: PicInfo, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 160.dp)
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(picInfo.url)
                .crossfade(true)
                .build(),
            contentDescription = picInfo.copyright,
        ) {
            when (val state = painter.state) {
                is AsyncImagePainter.State.Loading -> {
                    CircularProgressIndicator()
                }

                is AsyncImagePainter.State.Error -> Text("${state.result.throwable}")
                is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
                is AsyncImagePainter.State.Empty -> Text("Empty")
            }
        }
        Text(
            modifier = modifier
                .padding(vertical = 15.dp)
                .align(Alignment.CenterHorizontally),
            text = picInfo.copyright,
            color = Color.White,
            fontSize = 14.sp
        )
    }
}

@Composable
fun PicOfDayItemShow(picInfo: PicInfo, modifier: Modifier = Modifier) {
    // 如果没有主动设置图片装载到内存时的像素尺寸
    // SubcomposeAsyncImage 组件会默认根据当前组件布局的约束空间来确定图片最终装载的尺寸
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(picInfo.url)
            .crossfade(true)
            .build(),
        contentDescription = picInfo.copyright,
    ) {
        when (val state = painter.state) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator()
            }

            is AsyncImagePainter.State.Error -> Text("${state.result.throwable}")
            is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
            is AsyncImagePainter.State.Empty -> Text("Empty")
        }
    }
    Text(
        modifier = modifier
            .padding(vertical = 10.dp),
        text = picInfo.copyright,
        color = Color.White,
        fontSize = 14.sp
    )
}