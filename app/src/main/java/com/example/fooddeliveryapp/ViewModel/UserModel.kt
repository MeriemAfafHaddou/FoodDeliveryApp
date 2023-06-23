package com.example.fooddeliveryapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.Entity.Client
import com.example.fooddeliveryapp.LoginRequest
import com.example.fooddeliveryapp.Retrofit.RestaurantService
import com.example.fooddeliveryapp.Retrofit.UserService
import kotlinx.coroutines.*

class UserModel:ViewModel() {
    var user=MutableLiveData<Client>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch   {
            loading.value = false
            errorMessage.value = "Une erreur s'est produite"
        }
    }

    fun login(req:LoginRequest){
        if (user.value==null){
            loading.value=true
            CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
                val response = UserService.createEndpoint().login(req)
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        user.value=response.body()
                        print("Nchoufou yla l user null "+user.value)
                    } else {
                        errorMessage.value="Une erreur s'est produite"
                    }
                }
            }
        }
        }

    fun register(req:Client){
        if (user.value==null){
            loading.value=true
            CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
                val response = UserService.createEndpoint().register(req)
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        user.value=response.body()
                        print(user.value)
                    } else {
                        errorMessage.value="Une erreur s'est produite"
                    }
                }
            }
        }
    }



}