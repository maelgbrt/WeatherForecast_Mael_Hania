package com.example.weatherforecast

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresPermission


import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.data.SourceLocation
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.weatherforecast.ui.feature.home.HomeScreen
import com.example.weatherforecast.ui.theme.GoGoPowerRangerTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoGoPowerRangerTheme {
                HomeScreen(LocalContext.current)
            }
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        this.getLastKnownLocation()
    }

    private fun getLastKnownLocation() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            fusedLocationClient.lastLocation.addOnSuccessListener { loc ->
                    Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show()


                }.addOnFailureListener { err ->
                    err.message?.let { Log.d("Permission", it) }
                }
        }
    }


    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun HomeScreenPreview() {
        GoGoPowerRangerTheme {
            HomeScreen(LocalContext.current)
        }
    }
}