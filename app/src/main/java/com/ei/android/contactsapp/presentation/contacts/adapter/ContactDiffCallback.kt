package com.ei.android.contactsapp.presentation.contacts.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ei.android.contactsapp.presentation.contacts.ContactUi

class ContactDiffCallback:DiffUtil.ItemCallback<ContactUi>() {
    override fun areItemsTheSame(oldItem: ContactUi, newItem: ContactUi): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: ContactUi, newItem: ContactUi): Boolean {
        return oldItem == newItem
    }
}