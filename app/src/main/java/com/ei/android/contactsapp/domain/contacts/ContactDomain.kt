package com.ei.android.contactsapp.domain.contacts

import com.ei.android.contactsapp.core.Abstract
import com.ei.android.contactsapp.presentation.ContactUi

data class ContactDomain(
    private val id: String,
    private val lookupKey: String,
    private val name: String,
    private val starred: Boolean,
    private val photoUri: String?
) : Abstract<ContactUi, ContactDomainToUiMapper> {
    override fun map(mapper: ContactDomainToUiMapper) =
        mapper.map(id, lookupKey, name, starred, photoUri)
}