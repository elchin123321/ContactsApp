package com.ei.android.contactsapp.presentation.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ei.android.contactsapp.databinding.ContactElementBinding
import com.ei.android.contactsapp.presentation.contacts.ContactUi


class ContactAdapter: ListAdapter<ContactUi, ContactViewHolder>(ContactDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ContactElementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact)
    }
}