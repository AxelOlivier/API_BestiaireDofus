package fr.tuto.dofusapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.tuto.dofusapi.ApiInterface
import fr.tuto.dofusapi.dataClass.Monster
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TypeMonsterActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_monster)
        var recyclerView = findViewById<RecyclerView>(R.id.r_ViewTypeMonster)
        recyclerAdapter= RecyclerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        var apiInterface = ApiInterface.create().getMonstersList()
        apiInterface.enqueue(object : Callback<List<Monster>> {
            override fun onResponse(call: Call<List<Monster>>, response: Response<List<Monster>>) {
                if (response.body() != null) {
                    var jack = response.body()!!.distinctBy { it.type }
                    recyclerAdapter.setTypeMonsters(jack)
                }
            }
            override fun onFailure(call: Call<List<Monster>>, t: Throwable) {
                Log.e("Fail","FailureTypeMonsterActivity")
            }
        })

    }
}