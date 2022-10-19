package com.ei.android.contactsapp.presentation.contacts.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ei.android.contactsapp.databinding.ContactElementBinding
import com.ei.android.contactsapp.presentation.contacts.ContactUi

class ContactViewHolder(
    val binding: ContactElementBinding
) : RecyclerView.ViewHolder(binding.root){
    private val mapper = ToUiMapper.Base(binding)
    fun bind(item: ContactUi){
        item.map(mapper)
    }
}