package com.example.jobportal.common

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

fun EditText.onTextChange(textInputLayout: TextInputLayout?) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            textInputLayout?.isErrorEnabled = false
            textInputLayout?.error = null
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    })
}

// A placeholder username validation check
fun isEmailValid(email: String): Boolean {
    return if (email.contains("@")) {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    } else {
        email.isNotBlank()
    }
}

// A placeholder password validation check
fun isPasswordValid(password: String): Boolean {
    return password.length > 5
}

fun isNameValid(name:String):Boolean{
    return (name.length <= 4 || name.length >= 50)
}

fun isCompanyNameValid(name:String):Boolean{
    return (name.length > 100)
}
