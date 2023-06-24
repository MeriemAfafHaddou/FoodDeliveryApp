package com.example.fooddeliveryapp.Fragments
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.fooddeliveryapp.ViewModel.RestaurantModel
import com.example.fooddeliveryapp.databinding.FragmentDeliveryEndBinding
import com.example.fooddeliveryapp.sendEmail
import com.example.fooddeliveryapp.startCall
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
class FragmentDelivery : Fragment(), OnMapReadyCallback {
    lateinit var binding: FragmentDeliveryEndBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var restaurantModel: RestaurantModel
    private lateinit var mapView: MapView
    private lateinit var map: GoogleMap
    lateinit var userLocationMarker: MarkerOptions
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentDeliveryEndBinding.inflate(layoutInflater)
        mapView=binding.mapView
        mapView.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        mapView.getMapAsync(this)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantModel= ViewModelProvider(requireActivity()).get(RestaurantModel::class.java)
        mapView.getMapAsync { googleMap ->
            // Map is ready, you can perform map-related operations here
        }
        try {
            MapsInitializer.initialize(requireActivity())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val person=restaurantModel.person.value
        binding.apply {
            deliveryPhone.text=person?.numTel
        }
    }
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }
    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        // Request location permission if not granted
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        // Enable user's location on the map
        map.isMyLocationEnabled = true
        // Get user's last known location and display on the map
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val userLatLng = LatLng(location.latitude, location.longitude)
                userLocationMarker = MarkerOptions().position(userLatLng).title("Your Location")
                map.addMarker(userLocationMarker)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, DEFAULT_ZOOM))
            }
        }
    }
    companion object {
        private const val DEFAULT_ZOOM = 15f
        private const val LOCATION_PERMISSION_REQUEST_CODE = 123
    }
}