package com.ei.android.contactsapp.domain.contacts

import com.ei.android.contactsapp.core.Mapper
import com.ei.android.contactsapp.presentation.contacts.ContactUi

interface ContactDomainToUiMapper: Mapper{
    fun map(id: String,
            lookupKey: String,
            name: String,
            starred:Boolean,
            photoUri:String?
    ): ContactUi
}