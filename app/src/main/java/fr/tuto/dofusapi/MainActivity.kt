package fr.tuto.dofusapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(),CoroutineScope {

    private lateinit var userName: AppCompatEditText
    private lateinit var userDao: UserDao
    private lateinit var accessDatabase: UserRoomDatabase


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btn_start)
        userName = findViewById(R.id.cpt_pseudo)


        val intent : Intent = Intent(this, TypeMonsterActivity::class.java)
        button.setOnClickListener {
            val user = User(userName.text.toString())
            launch {
                accessDatabase()
                userDao.insert(user)
                Log.e("tesf","success")
            }

            startActivity(intent)
        }
    }
    fun accessDatabase(){
       accessDatabase =  UserRoomDatabase.getDatabase(this)
        userDao = accessDatabase.userDao()
    }
}


