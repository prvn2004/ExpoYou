package com.example.expoyou

import android.graphics.Insets.add
import androidx.core.graphics.Insets.add
import androidx.core.view.OneShotPreDrawListener.add
import com.google.android.gms.common.util.WorkSourceUtil.add


data class DataFile( val UrlValue: String? = "this is ") {
    private  var urlValue: String

    init {
        this.urlValue = UrlValue!!
    }

    fun getUrl(): String? {
        return urlValue
    }

    fun setUrl(Url: String?) {
        urlValue = Url.toString()
    }

}
