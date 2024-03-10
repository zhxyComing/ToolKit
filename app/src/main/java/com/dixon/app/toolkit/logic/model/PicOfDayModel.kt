package com.dixon.app.toolkit.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 每日一图接口
 *
 * {
 *     "code":200,
 *     "result":[
 *         {
 *             "copyright":"月亮升起，图森，亚利桑那州，美国 (© Tim Murphy/Shutterstock)",
 *             "copyrightlink":"https://www.bing.com/search?q=%E7%B2%89%E7%BA%A2%E8%89%B2%E6%9C%88%E4%BA%AE&form=hpcapt&mkt=zh-cn",
 *             "title":"亚利桑那州空中的粉月亮",
 *             "url":"https://cn.bing.com/th?id=OHR.ArizonaPinkMoon_ZH-CN5545607389_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp"
 *         },
 *         {
 *             "copyright":"杭州西湖水墨意境般的风景，浙江省，中国 (© zhangshuang/Getty Images)",
 *             "copyrightlink":"https://www.bing.com/search?q=%E6%B8%85%E6%98%8E%E8%8A%82&form=hpcapt&mkt=zh-cn",
 *             "title":"水墨西湖",
 *             "url":"https://cn.bing.com/th?id=OHR.QingMing2023_ZH-CN6951199028_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp"
 *         },
 *         {
 *             "copyright":"科尔多瓦的古罗马桥，西班牙 (© Jeremy Woodhouse/Getty Images)",
 *             "copyrightlink":"https://www.bing.com/search?q=%E7%A7%91%E5%B0%94%E5%A4%9A%E7%93%A6&form=hpcapt&mkt=zh-cn",
 *             "title":"这座古桥在哪呢？",
 *             "url":"https://cn.bing.com/th?id=OHR.RomanBridge_ZH-CN4699931052_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp"
 *         },
 *         {
 *             "copyright":"大岛上的霍瑙瑙国家历史公园，夏威夷 (© Westend61/Getty Images)",
 *             "copyrightlink":"https://www.bing.com/search?q=%E9%9C%8D%E7%91%99%E7%91%99%E5%9B%BD%E5%AE%B6%E5%8E%86%E5%8F%B2%E5%85%AC%E5%9B%AD&form=hpcapt&mkt=zh-cn",
 *             "title":"追逐彩虹",
 *             "url":"https://cn.bing.com/th?id=OHR.HonaunauNP_ZH-CN4491662962_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp"
 *         },
 *         {
 *             "copyright":"爪哇岛东部的婆罗摩火山，印度尼西亚 (© Bento Fotography/Getty Images)",
 *             "copyrightlink":"https://www.bing.com/search?q=%E5%A9%86%E7%BD%97%E6%91%A9%E7%81%AB%E5%B1%B1&form=hpcapt&mkt=zh-cn",
 *             "title":"这座缥缈的山在哪里？",
 *             "url":"https://cn.bing.com/th?id=OHR.JavaBromo_ZH-CN2744043733_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp"
 *         },
 *         {
 *             "copyright":"爪哇树蛙 (© kuritafsheen/Getty Images)",
 *             "copyrightlink":"https://www.bing.com/search?q=%E7%88%AA%E5%93%87%E6%A0%91%E8%9B%99&form=hpcapt&mkt=zh-cn",
 *             "title":"一只青翠碧绿的蛙",
 *             "url":"https://cn.bing.com/th?id=OHR.FrogMonth_ZH-CN3874143397_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp"
 *         },
 *         {
 *             "copyright":"斯太尔河,
 *              奥地利 (© guenterguni/Getty Images)",
 *             "copyrightlink":"https://www.bing.com/search?q=%E6%96%AF%E5%A4%AA%E5%B0%94%E6%B2%B3+%E5%A5%A5%E5%9C%B0%E5%88%A9&form=hpcapt&mkt=zh-cn",
 *             "title":"大自然的蓝色奇观",
 *             "url":"https://cn.bing.com/th?id=OHR.SteyrRiver_ZH-CN3175702026_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp"
 *         },
 *         {
 *             "copyright":"孔雀羽毛 (© sarayut Thaneerat/Getty Images)",
 *             "copyrightlink":"https://www.bing.com/search?q=%E5%AD%94%E9%9B%80%E7%BE%BD%E6%AF%9B&form=hpcapt&mkt=zh-cn",
 *             "title":"华贵的色彩",
 *             "url":"https://cn.bing.com/th?id=OHR.PeacockFeathers_ZH-CN3403145691_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp"
 *         }
 *     ],
 *     "msg":"success"
 * }
 */

data class PicOfDayResponse(val code: Int, val result: List<PicInfo>)

data class PicInfo(
    val copyright: String = "", // 版权信息
    @SerializedName("copyrightlink")
    val copyrightLink: String = "", // 版权地址URL
    val title: String = "",
    val url: String = ""
)