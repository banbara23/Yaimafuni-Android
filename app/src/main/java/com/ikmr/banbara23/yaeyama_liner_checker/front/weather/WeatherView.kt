package com.ikmr.banbara23.yaeyama_liner_checker.front.weather

import android.content.Context
import com.ikmr.banbara23.yaeyama_liner_checker.front.base.BaseView

/**
 * 港詳細のViewインターフェイス
 */
interface WeatherView : BaseView {
    fun openBrowser()
    fun getContext(): Context
}
