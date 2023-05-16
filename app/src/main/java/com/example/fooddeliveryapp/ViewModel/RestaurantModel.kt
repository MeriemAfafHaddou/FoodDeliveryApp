package com.example.fooddeliveryapp.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.Entity.Menu
import com.example.fooddeliveryapp.Entity.Restaurant
import com.example.fooddeliveryapp.Retrofit.RestaurantService
import kotlinx.coroutines.*

class RestaurantModel: ViewModel() {
    var restaurants= MutableLiveData<List<Restaurant>>()
    var menu=MutableLiveData<List<Menu>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch   {
            loading.value = false
            errorMessage.value = "Une erreur s'est produite"
        }
    }

    fun loadRestaurants() {
        if (restaurants.value==null){
            loading.value = true
            CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
                val response = RestaurantService.createEndpoint().getAllRestaurants()
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        restaurants.value=response.body()
                    } else {
                        errorMessage.value="Une erreur s'est produite"
                    }
                }
            }
        }
    }
    fun loadMenus(idRestaurant:Int) {
        if (menu.value==null){
            loading.value=true
            CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
                val response = RestaurantService.createEndpoint().getMenuByRestaurant(idRestaurant)
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        menu.value=response.body()
                    } else {
                        errorMessage.value="Une erreur s'est produite"
                    }
                }
            }
        }
    }
}