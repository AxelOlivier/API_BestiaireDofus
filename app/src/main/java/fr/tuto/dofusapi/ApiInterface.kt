package fr.tuto.dofusapi

//Creer une classe Monster et l'importer ici
//import fr.tuto.dofusapi.dataClass.Monster
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    // venir enlever le commentaire dès que le model Model a ete cree
   // @GET("/monsters")
   // fun getMonstersList(): Call<List<Monster>>

    companion object {
        private val BaseUrl = "https://fr.dofus.dofapi.fr/"

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }

    }


}