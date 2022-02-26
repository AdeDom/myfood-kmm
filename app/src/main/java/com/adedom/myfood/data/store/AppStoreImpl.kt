package com.adedom.myfood.data.store

import android.content.SharedPreferences
import androidx.core.content.edit
import com.adedom.core.data.store.AppStore

class AppStoreImpl(
    private val sharedPreferences: SharedPreferences
) : AppStore {

    override var accessToken: String?
        get() = sharedPreferences.getString("accessToken", "")
        set(value) {
            sharedPreferences.edit {
                putString("accessToken", value)
            }
        }

    override var refreshToken: String?
        get() = sharedPreferences.getString("refreshToken", "")
        set(value) {
            sharedPreferences.edit {
                putString("refreshToken", value)
            }
        }
}