package com.example.jobportal.common.shared_cache


import android.content.Context
import android.content.SharedPreferences
import com.example.jobportal.R

class MySharedPrefs(context: Context?) {

    var sharedPref: SharedPreferences? = null

    init {
        sharedPref = context?.getSharedPreferences(
            context.resources.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }

    private fun putData(key: String?, value: String?) {
        val editor = sharedPref?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    private fun getData(key: String?, defValue: String? = ""): String? {
        return sharedPref?.getString(key, defValue)
    }

    private fun putDataBoolean(key: String?, value: Boolean?) {
        val editor = sharedPref?.edit()
        editor?.putBoolean(key, value!!)
        editor?.apply()
    }

    private fun getDataBoolean(key: String?, defValue: Boolean? = false): Boolean? {
        return sharedPref?.getBoolean(key, defValue!!)
    }

    //    Delivery Address data start
    //name
    var id: String?
        get() = getData(DA_NAME)
        set(value) = putData(DA_NAME, value)


    //User Token to access everything on Server
    var token: String?
        get() = getData(TOKEN)
        set(value) = putData(TOKEN, value)

    //User Name
    var name: String?
        get() = getData(USER_NAME)
        set(value) = putData(USER_NAME, value)

    //User Email
    var email: String?
        get() = getData(USER_EMAIL)
        set(value) = putData(USER_EMAIL, value)

    //User Mobile Number
    var mobile: String?
        get() = getData(MOBILE)
        set(value) = putData(MOBILE, value)

    //User Profile url link
    var uicon: String?
        get() = getData(USER_IMAGE)
        set(value) = putData(USER_IMAGE, value)

    //User Total Silver Coin
    var userType: String?
        get() = getData(SCOIN)
        set(value) = putData(SCOIN, value)

    //User Total Gold Coin
    var companyName: String?
        get() = getData(GCOIN)
        set(value) = putData(GCOIN, value)

    fun onClear() {
        val edit = sharedPref?.edit()
        edit?.clear()?.apply()
    }

    companion object {
        private const val SCOIN = "sCoin.com"
        private const val GCOIN = "gCoin.com"
        private const val TOKEN = "token"
        private const val USER_IMAGE = "user_image"
        private const val USER_EMAIL = "user_email"
        private const val USER_NAME = "user_name"
        private const val MOBILE = "mobile"

        //Delivery Address
        private const val DA_NAME = "@DANAME"
    }

}