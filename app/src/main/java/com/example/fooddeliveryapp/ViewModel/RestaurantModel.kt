package com.example.fooddeliveryapp.ViewModel
import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.Entity.Commande
import com.example.fooddeliveryapp.Entity.DeliveryPerson
import com.example.fooddeliveryapp.Entity.Menu
import com.example.fooddeliveryapp.Entity.Restaurant
import com.example.fooddeliveryapp.Entity.Review
import com.example.fooddeliveryapp.Retrofit.RestaurantService
import kotlinx.coroutines.*

class RestaurantModel: ViewModel() {
    var restaurants= MutableLiveData<List<Restaurant>>()
    var menu=MutableLiveData<List<Menu>>()
    var person=MutableLiveData<DeliveryPerson>()
    var review=MutableLiveData<List<Review>>()
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
    fun validateCommand(commande:Commande) {
        print("\n commande : $commande \n")
        loading.value = true
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = RestaurantService.createEndpoint().validateCommand(commande)
            withContext(Dispatchers.Main) {
                loading.value = false
                if (response.isSuccessful && response.body() != null) {
                    person.value = response.body()
                } else {
                    errorMessage.value = response.message()
                }
            }
        }
    }

    fun loadReviews(idRestaurant:Int) {

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
    fun rateRestaurant(review: Review) {

        loading.value = true
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = RestaurantService.createEndpoint().rateRestaurant(review)
            withContext(Dispatchers.Main) {
                loading.value = false
                if (response.isSuccessful && response.body() != null) {
                    errorMessage.value = response.body()
                } else {
                    errorMessage.value = response.message()
                }
            }
        }


    }

}