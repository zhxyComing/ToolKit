package com.dixon.app.toolkit.logic.model

/**
 * 翻译接口
 * {
 *   "code": 200,
 *   "result": {
 *     "sourceText": "measurement",
 *     "targetText": "测量 / 尺寸 / 数量 / 测量单位",
 *     "source": "en",
 *     "target": "zh"
 *   },
 *   "msg": "success"
 * }
 */

data class TranslateResponse(val code: Int, val result: TranslateResult)

data class TranslateResult(
    val sourceText: String = "", // 查询词
    val targetText: String = "", // 翻译结果
    val source: String = "", // 查询语种
    val target: String = "", // 翻译语种
)