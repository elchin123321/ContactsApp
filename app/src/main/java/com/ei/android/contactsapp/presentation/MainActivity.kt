package com.ei.android.contactsapp.presentation

import android.Manifest.permission.READ_CONTACTS
import android.Manifest.permission.WRITE_CONTACTS
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ei.android.contactsapp.R
import com.ei.android.contactsapp.databinding.ActivityMainBinding
import com.ei.android.contactsapp.presentation.contacts.ContactListFragment
import com.ei.android.contactsapp.presentation.contacts.StarredContactListFragment
import com.ei.android.contactsapp.presentation.permissions.NoPermissionFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val fragment = chooseByNavigationFragment(bottomNavigation)
        setFragment(fragment)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_contacts -> {
                    checkPermission(
                        REQUEST_CODE_PERMISSION_READ_CONTACTS,
                        ContactListFragment.newInstance(),
                        READ_CONTACTS,
                        WRITE_CONTACTS
                    )
                    Log.d("ITEM1", item.toString())
                    true
                }
                R.id.page_favorites -> {
                    checkPermission(
                        REQUEST_CODE_PERMISSION_READ_CONTACTS,
                        StarredContactListFragment.newInstance(),
                        READ_CONTACTS,
                        WRITE_CONTACTS
                    )
                    Log.d("ITEM2", item.toString())

                    true
                }
                else -> false
            }
        }
    }

    fun chooseByNavigationFragment(bottomNavigation: BottomNavigationView): Fragment {
        val fragment = when (bottomNavigation.selectedItemId) {
            R.id.page_favorites -> StarredContactListFragment.newInstance()
            R.id.page_contacts -> ContactListFragment.newInstance()
            else -> throw RuntimeException("Illegal item selected")
        }
        return fragment
    }

    private fun checkPermission(
        permissionCode: Int,
        fragment: Fragment,
        vararg permissions: String,
    ) {
        val permissionStatus = checkMultiplePermissions(*permissions)
        if (permissionStatus) {
            setFragment(fragment)
        } else {
            requestPermissions(
                permissions,
                permissionCode
            )
            setFragment(NoPermissionFragment.newInstance(permissions))

        }
    }

    private fun checkMultiplePermissions(vararg permissions: String): Boolean {
        permissions.forEach {
            if (checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentHolder.id, fragment)
            .addToBackStack(null)
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
                    setFragment(NoPermissionFragment.newInstance(permissions))

                }
        }
    }

    companion object {
        private const val REQUEST_CODE_PERMISSION_READ_CONTACTS = 1
    }


}