package com.example.fooddeliveryapp.ViewModel

import androidx.lifecycle.MutableLiveData
import com.example.fooddeliveryapp.Entity.User
import com.example.fooddeliveryapp.Retrofit.RestaurantService
import com.example.fooddeliveryapp.Retrofit.UserService
import kotlinx.coroutines.*

class UserModel {
    var user=MutableLiveData<User>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch   {
            loading.value = false
            errorMessage.value = "Une erreur s'est produite"
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