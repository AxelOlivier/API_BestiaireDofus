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

var typeSelect : String? = null

class MonsterActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: RecyclerAdapterMonster

    override fun onCreate(instance: Bundle?) {
        super.onCreate(instance);
        setContentView(R.layout.activity_monster);

        var monsterList : ArrayList<Monster> = ArrayList();
        var minHP: Double= 0.0;
        var maxHP: Double= 0.0;
        var minPA: Double= 0.0;
        var maxPA: Double= 0.0;
        var minPM: Double= 0.0;
        var maxPM: Double= 0.0;

        var extras = intent.extras;
        if (extras != null) {
             typeSelect = extras.getString("type");
        }

        var recyclerView = findViewById<RecyclerView>(R.id.r_ViewMonster);

        recyclerAdapter= RecyclerAdapterMonster(this);
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.adapter = recyclerAdapter;

        var apiInterface = ApiInterface.create().getMonstersList();
        apiInterface.enqueue(object : Callback<List<Monster>> {
            override fun onResponse(call: Call<List<Monster>>, response: Response<List<Monster>>) {
                if (response.body() != null) {
                    var allMonster = response.body();
                    if (allMonster != null) {
                        for (monstreType in allMonster){
                            if(typeSelect == monstreType.type){
                                var stats = monstreType.stat
                                for (stat in stats){
                                    var pv = stat.pv;
                                    var pa = stat.pa;
                                    var pm = stat.pm;
                                        if(pv !=null){
                                             minHP = pv.min;
                                             maxHP = pv.max;
                                        }
                                        if(pa !=null){
                                            minPA = pa.min;
                                            maxPA = pa.max;
                                        }
                                        if(pm !=null){
                                            minPM = pm.min;
                                            maxPM = pm.max;
                                        }
                                    }
                                monsterList.add(Monster("",monstreType.name,monstreType.imgUrl,
                                    monstreType.stat, minHP, maxHP,minPA, maxPA,minPM,maxPM))
                            }
                        }
                    }
                    recyclerAdapter.setMonsters(monsterList)
                }
            }
            override fun onFailure(call: Call<List<Monster>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}