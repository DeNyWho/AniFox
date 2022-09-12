package com.example.anifox.util.validation

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import com.example.anifox.R
import com.example.anifox.common.listeners.ItemListenerUserName
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AuthValidate(private val context: Context){

    private fun validateNickName(type: Int, tie: View, til: View, onListen: ItemListenerUserName?): Boolean {
        val nickName = tie.findViewById<TextInputEditText>(R.id.etNickName)
        val nickNameTil = til.findViewById<TextInputLayout>(R.id.etNickNameTIL)
        if(type == 1){
            var result = false
            if (nickName.text.toString().length in 4..20) {
                onListen!!.postUserName(nickName.text.toString())
                result = onListen.getUserName()
            }
            if (nickName.text.toString().trim().isEmpty()) {
                nickNameTil.helperText = context.getString(R.string.require_field)
                return false
            } else if(nickName.text.toString().length < 3 || nickName.text.toString().length > 20){
                nickNameTil.helperText = context.getString(R.string.require_nicknameSize)
                return false
            } else if (nickName.text.toString().length in 4..20) {
                onListen!!.postUserName(nickName.text.toString())
            } else if (result) {
                nickNameTil.error = context.getString(R.string.username_already_registered)
            } else {
                nickNameTil.isHelperTextEnabled = false
                nickNameTil.isErrorEnabled = false
            }
            return true
        } else {
            if (nickName.text.toString().trim().isEmpty()) {
                nickNameTil.error = context.getString(R.string.require_field)
                nickName.requestFocus()
                return false
            } else if(nickName.text.toString().length < 3 || nickName.text.toString().length > 20){
                nickNameTil.error = context.getString(R.string.require_nicknameSize)
                nickName.requestFocus()
                return false
            } else {
                nickNameTil.isErrorEnabled = false
            }
            return true
        }
    }

    private fun validatePassword(type: Int, tie: View, til: View): Boolean {
        val password = tie.findViewById<TextInputEditText>(R.id.etPassword)
        val passwordTil = til.findViewById<TextInputLayout>(R.id.etPasswordTIL)
        when (type) {
            1 -> {
                if (password.text.toString().trim().isEmpty()) {
                    passwordTil.helperText = context.getString(R.string.require_field)
                    return false
                } else if (password.text.toString().length < 8) {
                    passwordTil.helperText = "${context.getString(R.string.require_password)} ${ 8 - password.text.toString().length} ${context.getString(R.string.require_symbols)}"
                } else {
                    passwordTil.isHelperTextEnabled = false
                }
                return true
            }
            else -> {
                if (password.text.toString().trim().isEmpty()) {
                    passwordTil.error = context.getString(R.string.require_field)
                    password.requestFocus()
                    return false
                } else if (password.text.toString().length < 8) {
                    passwordTil.error = "${context.getString(R.string.require_password)} ${ 8 - password.text.toString().length} ${context.getString(R.string.require_symbols)}"
                    password.requestFocus()
                } else {
                    passwordTil.isErrorEnabled = false
                }
                return true
            }
        }
    }

    private fun validateConfirmPassword(type: Int, pass: View, til: View, tie: View): Boolean {
        val password = pass.findViewById<TextInputEditText>(R.id.etPassword)
        val passwordConfirm = tie.findViewById<TextInputEditText>(R.id.etPasswordConfirm)
        val passwordConfirmTil = til.findViewById<TextInputLayout>(R.id.etPasswordConfirmTIL)
        if(type == 1){
            if(passwordConfirm.text.toString().trim().isEmpty()) {
                passwordConfirmTil.helperText = context.getString(R.string.require_field)
                return false
            } else if(passwordConfirm.text.toString() != password.text.toString()) {
                passwordConfirmTil.helperText = context.getString(R.string.password_confirm_helper)
                return false
            } else  {
                passwordConfirmTil.isErrorEnabled = false
            }
        } else {
            if(passwordConfirm.text.toString().trim().isEmpty()) {
                passwordConfirmTil.error = context.getString(R.string.require_field)
                passwordConfirm.requestFocus()
                return false
            } else if(passwordConfirm.text.toString() != password.text.toString()) {
                passwordConfirmTil.error = context.getString(R.string.password_confirm_helper)
                passwordConfirm.requestFocus()
                return false
            } else  {
                passwordConfirmTil.isErrorEnabled = false
            }
        }
        return true
    }
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun existEmailError(tie: View,til: View): Boolean {
        val emailTil = til.findViewById<TextInputLayout>(R.id.etEmailTIL)
        val emailTie = tie.findViewById<TextInputEditText>(R.id.etEmail)
        emailTil.error = context.getString(R.string.email_already_registered)
        emailTie.requestFocus()
        return true
    }

    private fun existNameError(tie: View, til: View, result: Boolean): Boolean {
        val nameTil = til.findViewById<TextInputLayout>(R.id.etNickNameTIL)
        val nameTie = tie.findViewById<TextInputEditText>(R.id.etNickName)
        nameTil.error = context.getString(R.string.username_already_registered)
        nameTie.requestFocus()
        return true
    }

    private fun validateEmail(type: Int, tie: View, til: View): Boolean {
        val email = tie.findViewById<TextInputEditText>(R.id.etEmail)
        val emailTil = til.findViewById<TextInputLayout>(R.id.etEmailTIL)
        if (type == 1) {
            if (email.text.toString().trim().isEmpty()) {
                emailTil.isErrorEnabled = false
                emailTil.helperText = context.getString(R.string.require_field)
                return false
            } else if (!isValidEmail(email.text.toString())) {
                emailTil.isErrorEnabled = false
                emailTil.helperText = context.getString(R.string.email_wrong)
                return false
            } else {
                emailTil.isHelperTextEnabled = false
            }
            return true
        } else {
            if (email.text.toString().trim().isEmpty()) {
                emailTil.error = context.getString(R.string.require_field)
                email.requestFocus()
                return false
            } else if (!isValidEmail(email.text.toString())) {
                emailTil.error = context.getString(R.string.email_wrong)
                email.requestFocus()
                return false
            } else {
                emailTil.isErrorEnabled = false
            }
            return true
        }
    }

    inner class ValidateSignUp {
        fun password(type: Int, tie: View, til: View): Boolean {
            return if(tie.id == R.id.etPassword && til.id == R.id.etPasswordTIL){
                 validatePassword(
                    type = type,
                    tie = tie,
                    til = til
                )
            } else false
        }
        fun email(type: Int, tie: View, til: View): Boolean {
            return if(tie.id == R.id.etEmail && til.id == R.id.etEmailTIL){
                 validateEmail(
                    type = type,
                    tie = tie,
                    til = til
                )
            } else false
        }
        fun nickName(type: Int, tie: View, til: View): Boolean {
            return if(tie.id == R.id.etNickName && til.id == R.id.etNickNameTIL){
                 validateNickName(
                     type = type,
                     tie = tie,
                     til = til,
                     onListen = null
                 )
            } else false
        }
        fun passwordConfirm(type: Int, tie: View, til: View, pass: View?): Boolean {
            return if(tie.id == R.id.etPasswordConfirm && til.id == R.id.etPasswordConfirmTIL && pass!!.id == R.id.etPassword){
                validateConfirmPassword(
                    type = type,
                    tie = tie,
                    til = til,
                    pass = pass
                )
            } else false
        }
        fun existEmail(tie: View, til: View): Boolean {
            return if(til.id == R.id.etEmailTIL){
                existEmailError(
                    tie = tie,
                    til = til
                )
            } else false
        }
        fun existName(tie: View, til: View, result: Boolean): Boolean {
            return if(til.id == R.id.etNickNameTIL){
                existNameError(
                    tie = tie,
                    til = til,
                    result = result
                )
            } else false
        }


    }

    inner class ValidateSignIn {
        fun password(type: Int, tie: View, til: View): Boolean {
            return if(tie.id == R.id.etPassword && til.id == R.id.etPasswordTIL){
                 validatePassword(
                    type = type,
                    tie = tie,
                    til = til
                )
            } else false
        }

        fun passwordWrong(passwordTie: View, emailTie: View, passwordTil: View, emailTil: View): Boolean {
            return if(passwordTie.id == R.id.etPassword && emailTie.id == R.id.etEmail){
                val password = passwordTie.findViewById<TextInputEditText>(R.id.etPassword)
                val passwordTilling = passwordTil.findViewById<TextInputLayout>(R.id.etPasswordTIL)
                val email = emailTie.findViewById<TextInputEditText>(R.id.etEmail)
                val emailTilling = emailTil.findViewById<TextInputLayout>(R.id.etEmailTIL)
                passwordTilling.error = context.getString(R.string.wrong_password)
                password.requestFocus()
                emailTilling.error = context.getString(R.string.wrong_password)
                email.requestFocus()
            } else false
        }
        fun email(type: Int, tie: View, til: View): Boolean {
            return if(tie.id == R.id.etEmail && til.id == R.id.etEmailTIL){
                 validateEmail(
                    type = type,
                    tie = tie,
                    til = til
                )
            } else false
        }
    }

    inner class TextFieldValidation(
        private val type: Int,
        private val tie: View,
        private val til: View,
        private val pass: View?,
        private val onListen: ItemListenerUserName?
    ) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (tie.id == R.id.etEmail && til.id == R.id.etEmailTIL) validateEmail(
                type = type,
                tie = tie,
                til = til
            )
            if (tie.id == R.id.etPassword && til.id == R.id.etPasswordTIL) validatePassword(
                type = type,
                tie = tie,
                til = til
            )
            if (tie.id == R.id.etNickName && til.id == R.id.etNickNameTIL) validateNickName(
                type = type,
                tie = tie,
                til = til,
                onListen = onListen
            )
            if (tie.id == R.id.etPasswordConfirm && til.id == R.id.etPasswordConfirmTIL && pass!!.id == R.id.etPassword) validateConfirmPassword(
                type = type,
                tie = tie,
                til = til,
                pass = pass
            )
        }
    }
}