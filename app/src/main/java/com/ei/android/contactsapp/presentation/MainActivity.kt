package com.ei.android.contactsapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.ei.android.contactsapp.R
import com.ei.android.contactsapp.core.ContactsApp
import com.ei.android.contactsapp.databinding.ActivityMainBinding
import com.ei.android.contactsapp.presentation.contacts.ContactListFragment
import com.ei.android.contactsapp.presentation.contacts.ContactsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:ContactsViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val fragment = ContactListFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentHolder.id,fragment)
            .addToBackStack(null)
            .commit()
        viewModel = (application as ContactsApp).viewModel
        viewModel.observe(this){
            Log.d("Cool",it.toString())
        }
        viewModel.fetchContacts()
    }


}