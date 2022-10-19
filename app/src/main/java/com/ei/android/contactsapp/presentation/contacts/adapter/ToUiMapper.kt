package com.ei.android.contactsapp.presentation.contacts.adapter

import com.bumptech.glide.Glide
import com.ei.android.contactsapp.R
import com.ei.android.contactsapp.core.Mapper
import com.ei.android.contactsapp.databinding.ContactElementBinding

interface ToUiMapper : Mapper {
    fun map(
        name: String,
        starred: Boolean,
        photoUri: String?
    )

    class Base(private val binding: ContactElementBinding) : ToUiMapper {
        override fun map(name: String, starred: Boolean, photoUri: String?) {
            binding.contactName.text = name
            val starredImage =
                if (starred)
                    R.drawable.ic_star_filled
                else
                    R.drawable.ic_star_unfilled
            binding.contactStarred.setImageResource(starredImage)
            photoUri?.let {
                Glide
                    .with(binding.root)
                    .load(photoUri)
                    .into(binding.contactImage)
            } ?: Glide
                .with(binding.root)
                .load(R.drawable.ic_contact_icon)
                .into(binding.contactImage)
        }
    }
}