package com.ei.android.contactsapp.presentation

import android.Manifest.permission.READ_CONTACTS
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ei.android.contactsapp.R
import com.ei.android.contactsapp.databinding.ActivityMainBinding
import com.ei.android.contactsapp.presentation.contacts.ContactListFragment
import com.ei.android.contactsapp.presentation.permissions.NoPermissionFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        checkPermission(READ_CONTACTS)

    }

    private fun checkPermission(permission: String) {
        val permissionStatus = ContextCompat.checkSelfPermission(this, permission)
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            setFragment(ContactListFragment.newInstance())
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(permission),
                REQUEST_CODE_PERMISSION_READ_CONTACTS
            )
            setFragment(NoPermissionFragment.newInstance())
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentHolder.id, fragment)
            .commit()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_PERMISSION_READ_CONTACTS ->
                if (grantResults.size >= 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    setFragment(ContactListFragment.newInstance())
                } else {
                    setFragment(NoPermissionFragment.newInstance())

                }
        }
    }

    companion object {
        private const val REQUEST_CODE_PERMISSION_READ_CONTACTS = 1
    }


}