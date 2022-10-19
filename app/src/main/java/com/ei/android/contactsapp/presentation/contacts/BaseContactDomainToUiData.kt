package com.ei.android.contactsapp.presentation.contacts

import com.ei.android.contactsapp.domain.contacts.ContactDomainToUiMapper

class BaseContactDomainToUiData:ContactDomainToUiMapper {
    override fun map(
        id: String,
        lookupKey: String,
        name: String,
        starred: Boolean,
        photoUri: String?
    ): ContactUi {
        return ContactUi(id,lookupKey,name,starred,photoUri)//TODO add basePhotoURI
    }
}