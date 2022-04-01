package fr.tuto.dofusapi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(@PrimaryKey@ColumnInfo (name = "username") var username:String)
