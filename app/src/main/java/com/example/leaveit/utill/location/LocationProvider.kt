package com.example.leaveit.utill.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

object LocationProvider {
    private var locationManager: LocationManager? = null
    private var currentLocation: Location? = null

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    fun getCurrentLocation(context: Context, onLocationRetrieved: (Location?) -> Unit) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        val fineLocationStatus = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val coarseLocationStatus = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if (fineLocationStatus == PackageManager.PERMISSION_GRANTED ||
            coarseLocationStatus == PackageManager.PERMISSION_GRANTED) {

            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                onLocationRetrieved(location)
            }.addOnFailureListener {
                Log.d("LocationProvider", "Failed to get location: ${it.message}")
                onLocationRetrieved(null)
            }
        } else {
            Log.d("LocationProvider", "권한 없음")
            onLocationRetrieved(null)
        }
    }
}
