package com.dixon.app.toolkit.core.page.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 首页布局文件
 */
@Composable
fun HomeUi(
    onFunCardClick: (funType: FunType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeTitleShow()
        HomeCardShow(onFunCardClick = onFunCardClick)
    }
}

@Composable
fun HomeTitleShow(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 30.dp,
                start = 20.dp,
                bottom = 20.dp
            ),
        text = "简单工具",
        color = Color.White,
        fontSize = 30.sp
    )
}

/**
 * 功能卡片列表
 */
@Composable
fun HomeCardShow(onFunCardClick: (funType: FunType) -> Unit, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        // 固定两列 但是如果屏幕旋转会导致变形，所以一般指定最小宽度，由系统决定一行放几个
//        columns = GridCells.Fixed(3) ,
        columns = GridCells.Adaptive(100.dp),
        content = {
            items(FunType.values()) { funType ->
                Box(
                    modifier = Modifier
                        .height(120.dp)
                        .clip(RoundedCornerShape(14.dp)) // 设置圆角半径
                        .background(
                            brush = Brush.horizontalGradient( // 设置渐变色
                                listOf(
                                    Color(0xFFFFFFFF),
//                                    Color(0xFFF5DEC9),
//                                    Color(0xFFF7A74C),
                                    Color(0xFFFFFFFF),
                                )
                            )
                        )
                        .clickable {
                            onFunCardClick.invoke(funType)
                        }
                ) {
                    Text(
                        modifier = modifier.align(Alignment.Center),
                        text = funType.desc,
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        // 字间距
                        letterSpacing = TextUnit(1f, TextUnitType.Sp)
                    )
                }
            }
            // 设置某一Item占据最大宽度
            // item(span = {
            //        GridItemSpan(maxLineSpan)
            //    })
        },
        // 横纵间距
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    )
}