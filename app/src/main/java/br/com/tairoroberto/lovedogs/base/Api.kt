package br.com.tairoroberto.lovedogs.base

import br.com.tairoroberto.lovedogs.login.model.LoginResponse
import br.com.tairoroberto.lovedogs.petshop.model.PetShop
import br.com.tairoroberto.lovedogs.petshop.model.PetshopsResponse
import br.com.tairoroberto.lovedogs.petshop.model.UpdatePetShopResponse
import br.com.tairoroberto.lovedogs.petshopservice.model.ServicesResponse
import br.com.tairoroberto.lovedogs.register.model.UserRegisterRequest
import br.com.tairoroberto.lovedogs.register.model.UserRegisterResponse
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Created by tairo on 8/24/17.
 */
interface Api {

    @GET("/login/{email}/{password}")
    fun login(@Path("email") email: String, @Path("password") password: String): Observable<LoginResponse>

    @POST("/users")
    fun registerUser(@Body userRegisterRequest: UserRegisterRequest) : Observable<UserRegisterResponse>

    @GET("/petshops")
    fun getPetshops(): Observable<PetshopsResponse>

    @PUT("/petshops")
    fun updatePetshop(@Body petShop: PetShop): Observable<UpdatePetShopResponse>

    @GET("/favorites")
    fun getFavorites(): Observable<PetshopsResponse>

    @GET("/services/petshop")
    fun getServices(): Observable<ServicesResponse>
}

