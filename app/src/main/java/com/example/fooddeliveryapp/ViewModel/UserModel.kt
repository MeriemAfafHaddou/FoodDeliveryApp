package com.example.fooddeliveryapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.Entity.Client
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

    fun login(email:String, pwd:String){
        if (user.value==null){
            loading.value=true
            CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
                val response = UserService.createEndpoint().login(email,pwd)
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        user.value=response.body()
                    } else {
                        errorMessage.value="Une erreur s'est produite"
                    }
                }
            }
        }
        }

    fun getUserInfos(idUser:Int){
        if (user.value==null){
            loading.value = true
            CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
                val response = UserService.createEndpoint().getUserInfos(idUser)
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        user.value=response.body()
                    } else {
                        errorMessage.value="Une erreur s'est produite"
                    }
                }
            }
        }
    }


}