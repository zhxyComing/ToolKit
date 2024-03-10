package com.dixon.app.toolkit.core.page.translate

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dixon.app.toolkit.logic.model.TranslateResult
import com.dixon.app.toolkit.R
import com.dixon.app.toolkit.core.util.Ln

/**
 * 翻译页面布局文件
 */
@Composable
fun TranslateUi(
    translateResult: Result<TranslateResult>,
    onQueryChanged: (text: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TranslateTitleShow()
        TranslateQueryShow(onQueryChanged = onQueryChanged)
        TranslateResShow(translateResult = translateResult)
    }
}

@Composable
fun TranslateTitleShow(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 30.dp,
                start = 20.dp,
                bottom = 10.dp
            ),
        text = "简单翻译",
        color = Color.White,
        fontSize = 30.sp
    )
}

/**
 * 查询框
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TranslateQueryShow(
    onQueryChanged: (text: String) -> Unit,
    modifier: Modifier = Modifier
) {
    // State的变化会影响当前Compose的重组
    var translateText by remember { mutableStateOf("") }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = modifier.weight(3f),
            value = translateText,
            onValueChange = { string ->
                translateText = string
            },
            placeholder = {
                Text(
                    text = "输入翻译内容"
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                textColor = Color.Black,
                // 去掉下划线
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            // 让 TextField 显示成椭圆形
            // 输入框形状，圆角等
            shape = MaterialTheme.shapes.small.copy(
                topStart = CornerSize(45.dp),
                topEnd = CornerSize(45.dp),
                bottomEnd = CornerSize(45.dp),
                bottomStart = CornerSize(45.dp)
            ),
            // 监听点击回车
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                onQueryChanged.invoke(translateText)
                keyboardController?.hide()
            })
        )
        Button(
            modifier = modifier
                .padding(start = 10.dp)
                .weight(1f),
            onClick = {
                Ln.e("TranslateUi", "点击查询：$translateText")
                when {
                    translateText.isEmpty() -> Toast.makeText(
                        context,
                        "请输入翻译内容",
                        Toast.LENGTH_SHORT
                    ).show()
                    // 通过基本的验证 去查询
                    else -> onQueryChanged(translateText)
                }
            }) {
            Text(text = "翻译")
        }
    }
}

/**
 * 查询结果
 * <p>
 * 仅当数据有变化才会重组
 * 比如 18515412486 和 18515412489 俩个手机号，查询的结果相同，也不会触发重组
 * 从日志就可以看出
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TranslateResShow(
    translateResult: Result<TranslateResult>,
    modifier: Modifier = Modifier
) {
    Ln.e("TranslateUi", "获取结果：${translateResult.isSuccess}")
    if (translateResult.isSuccess) {
        val context = LocalContext.current
        translateResult.getOrNull()?.let { res ->
            // 请求成功
            Text(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .combinedClickable(
                        onLongClick = {
                            // 严格来说这段是长按后的业务逻辑，应该以回调形式交给上层处理
                            // 长按复制
                            val clip = ClipData.newPlainText("label", res.targetText)
                            val clipboard =
                                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            clipboard.setPrimaryClip(clip)
                            Toast
                                .makeText(context, "已复制到剪贴板", Toast.LENGTH_SHORT)
                                .show()
                        }, onClick = {

                        }),
                text = "${res.targetText}",
                color = colorResource(id = R.color.md_green_50),
//                fontWeight = FontWeight.Bold
            )
            Ln.e("TranslateUi", "获取成功：$res")
        }
    } else {
        Text(
            modifier = modifier.padding(horizontal = 10.dp),
            text = "请求失败：${translateResult.exceptionOrNull()}",
            color = Color.White
        )
    }
}