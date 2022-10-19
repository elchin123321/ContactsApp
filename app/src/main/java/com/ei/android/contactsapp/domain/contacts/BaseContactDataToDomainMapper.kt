package com.ei.android.contactsapp.domain.contacts

import com.ei.android.contactsapp.data.contacts.ContactDataToDomainMapper

class BaseContactDataToDomainMapper:ContactDataToDomainMapper {
    override fun map(
        id: String,
        lookupKey: String,
        name: String,
        starred: Int,
        photoUri: String?
    ): ContactDomain {
        val isStarred = starred == 1
        return ContactDomain(id,lookupKey,name,isStarred,photoUri)
    }
}