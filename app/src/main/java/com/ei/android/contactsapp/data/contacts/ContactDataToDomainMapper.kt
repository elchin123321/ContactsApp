package com.ei.android.contactsapp.data.contacts

import com.ei.android.contactsapp.core.Mapper
import com.ei.android.contactsapp.domain.contacts.ContactDomain

interface ContactDataToDomainMapper:Mapper{
    fun map(id: String,
            lookupKey: String,
            name: String,
            starred:Int,
            photoUri:String?
    ):ContactDomain
}