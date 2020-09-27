package com.example.demo

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.demo.fragments.ListFragment
import com.google.android.gms.location.FusedLocationProviderClient
import android.provider.Settings
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import com.example.demo.adapters.CountryAdapter
import com.example.demo.fragments.DetailFragment
import com.example.demo.fragments.WeatherFragment
import com.example.demo.models.Country
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), CountryAdapter.ClickEventHandler {

    private var mFusedLocationClient: FusedLocationProviderClient? = null
    protected var mLastLocation: Location? = null
    var iv_cloud: ImageView? = null

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
        private val TAG = "LocationProvider"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iv_cloud = findViewById(R.id.iv_cloud);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        supportFragmentManager.beginTransaction().replace(R.id.fl_fragment, ListFragment()).commit();

        iv_cloud?.setOnClickListener {
            if(mLastLocation != null){
                navigateWeatherFragment();
            }else{
                if (!checkPermissions()) {
                    requestPermissions()
                } else {
                    getLastLocation()
                }
            }
        }
    }

    private fun navigateWeatherFragment() {
        val bundle = Bundle()
        bundle.putString("lat", mLastLocation?.latitude.toString())
        bundle.putString("long", mLastLocation?.longitude.toString())

        val transaction = this.supportFragmentManager.beginTransaction()
        val weatherFragment = WeatherFragment()
        weatherFragment.arguments = bundle

        transaction.replace(R.id.fl_fragment, weatherFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        supportFragmentManager.beginTransaction().replace(R.id.fl_fragment, ListFragment())
            .commit();
    }

    override fun onStart() {
        super.onStart()

        if (!checkPermissions()) {
            requestPermissions()
        } else {
            getLastLocation()
        }
    }

    /**
     * Provides a simple way of getting a device's location and is well suited for
     * applications that do not require a fine-grained location and that do not need location
     * updates. Gets the best and most recent location currently available, which may be null
     * in rare cases when a location is not available.
     *
     *
     * Note: this method should be called after location permission has been granted.
     */
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        mFusedLocationClient!!.lastLocation
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful && task.result != null) {
                    mLastLocation = task.result

                    Log.w(TAG, "lat: "+ mLastLocation?.latitude + "lag: " + mLastLocation?.longitude)

                } else {
                    Log.w(TAG, "getLastLocation:exception", task.exception)
                    showMessage(getString(R.string.no_location_detected))
                }
            }
    }

    private fun showMessage(text: String) {
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_LONG).show()
    }

    private fun checkPermissions(): Boolean {
        val permissionState =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            PERMISSION_REQUEST_ACCESS_FINE_LOCATION
        )
    }

    private fun requestPermissions() {
        val shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {

            showSnackbar(R.string.permission_rationale, android.R.string.ok,
                View.OnClickListener {
                    // Request permission
                    startLocationPermissionRequest()
                })

        } else {
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            startLocationPermissionRequest()
        }
    }

    private fun showSnackbar(
        mainTextStringId: Int, actionStringId: Int,
        listener: View.OnClickListener
    ) {

        Toast.makeText(this@MainActivity, getString(mainTextStringId), Toast.LENGTH_LONG).show()
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.size <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i(TAG, "User interaction was cancelled.")
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                getLastLocation()
            } else {
                // Permission denied.

                // Notify the user via a SnackBar that they have rejected a core permission for the
                // app, which makes the Activity useless. In a real app, core permissions would
                // typically be best requested during a welcome-screen flow.

                // Additionally, it is important to remember that a permission might have been
                // rejected without asking the user for permission (device policy or "Never ask
                // again" prompts). Therefore, a user interface affordance is typically implemented
                // when permissions are denied. Otherwise, your app could appear unresponsive to
                // touches or interactions which have required permissions.
                showSnackbar(R.string.permission_denied_explanation, R.string.settings,
                    View.OnClickListener {
                        // Build intent that displays the App settings screen.
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri = Uri.fromParts(
                            "package",
                            BuildConfig.APPLICATION_ID, null
                        )
                        intent.data = uri
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    })
            }
        }

    }

    override fun forwardClick(country: Country) {
        val gson = Gson()
        val bundle = Bundle()
        bundle.putString("country",gson.toJson(country))

        val transaction = this.supportFragmentManager.beginTransaction()
        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle

        transaction.replace(R.id.fl_fragment, detailFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}