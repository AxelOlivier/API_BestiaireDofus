package fr.tuto.dofusapi

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.tuto.dofusapi.dataClass.Monster

class RecyclerAdapterMonster(var context: Context) : RecyclerView.Adapter<RecyclerAdapterMonster.ViewHolderMonster>() {

    var monsterType : List<Monster> = listOf()

    override fun getItemCount(): Int {
        return monsterType.size;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterMonster.ViewHolderMonster {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_monster,parent,false);
        return ViewHolderMonster(view);
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerAdapterMonster.ViewHolderMonster, position: Int) {
        holder.cptMonsterName.text = monsterType[position].name;
        holder.cptPV.text = "PV : ${monsterType[position].pvMin} - ${monsterType[position].pvMax}";
        holder.cptPA.text = "PA : ${monsterType[position].paMin} - ${monsterType[position].paMax}";
        holder.cptPM.text = "PM : ${monsterType[position].pmMin} - ${monsterType[position].pmMax}";


        var image = monsterType[position].imgUrl;
        var holderTest = holder.cptImage;
        Glide.with(context).load(image).into(holderTest);
    }

    fun setMonsters(monsterType: List<Monster>){
        this.monsterType = monsterType;
        notifyDataSetChanged();
    }

    inner class ViewHolderMonster(itemView: View): RecyclerView.ViewHolder(itemView){
        var cptImage: ImageView = itemView.findViewById(R.id.cpt_monsterImage);
        var cptMonsterName: TextView = itemView.findViewById(R.id.cpt_monsterName);
        var cptPV :TextView = itemView.findViewById(R.id.cpt_monsterPV);
        var cptPA :TextView = itemView.findViewById(R.id.cpt_monsterPA);
        var cptPM :TextView = itemView.findViewById(R.id.cpt_monsterPM);
    }
}