package com.example.fooddeliveryapp.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fooddeliveryapp.Entity.Menu
import com.example.fooddeliveryapp.Entity.Restaurant
import com.example.fooddeliveryapp.Entity.Review
import com.example.fooddeliveryapp.Retrofit.RestaurantService
import kotlinx.coroutines.*

class RestaurantModel: ViewModel() {
    lateinit var userModel: UserModel
    var restaurants= MutableLiveData<List<Restaurant>>()
    var menu=MutableLiveData<List<Menu>>()
    var review=MutableLiveData<List<Review>>()
    var details=MutableLiveData<Menu>()
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
                        //print restaurants.value to logcat
                        println(response.body())
                        println(restaurants.value)

                    } else {
                        errorMessage.value="Une erreur s'est produite"
                    }
                }
            }
        }
    }
    fun loadMenus(idRestaurant:Int) {
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

    fun loadReviews(idRestaurant:Int) {

        if (review.value==null){
            loading.value=true
            CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
                val response = RestaurantService.createEndpoint().getReviewsByRestaurant(idRestaurant)
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        review.value=response.body()
                    } else {
                        errorMessage.value="Une erreur s'est produite"
                    }
                }
            }
        }
    }

    fun loadDetails(idMenu:Int) {
        if (details.value==null){
            loading.value=true
            CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
                val response = RestaurantService.createEndpoint().getMenuDetails(idMenu)
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        details.value=response.body()
                    } else {
                        errorMessage.value="Une erreur s'est produite"
                    }
                }
            }
        }
    }
}