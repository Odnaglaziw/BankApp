package com.amok.mokeev_variant4

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class Bank : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank)
        val enter_button:AppCompatButton = findViewById(R.id.enter_button)
        enter_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val username = findViewById<EditText>(R.id.login).toString()
                val password = findViewById<EditText>(R.id.password).toString()
                val sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE)

                if (sharedPreferences.contains(username)){
                    saveData(username,password,sharedPreferences)
                    Toast.makeText(this@Bank, "Логин сохранён", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@Bank, "Логин уже существует", Toast.LENGTH_SHORT).show()
                }

                if (fillCheck(username,password,sharedPreferences)){
                    val storedPassword = sharedPreferences.getString(username,"")

                    if (storedPassword == password){
                        Toast.makeText(this@Bank, "Вход выполненл успешно", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@Bank, "Неверный пароль", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this@Bank, "Проверьте логин", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    fun saveData(username: String,password: String, sharedPreferences: SharedPreferences){
        val editor = sharedPreferences.edit()
        editor.putString(username,password)
        editor.apply()
    }
    fun fillCheck(username: String, password:String, sharedPreferences: SharedPreferences):Boolean{
        if (username.isEmpty()||password.isEmpty()){
            return false
        }
        if (password.length < 8){
            return false
        }
        if (!sharedPreferences.contains(username)){
            return false
        }
    return true
    }
}