package com.ei.android.contactsapp.domain.contacts

import android.provider.ContactsContract
import com.ei.android.contactsapp.core.Mapper
import com.ei.android.contactsapp.presentation.ContactUi

class ContactsInteractor(
    private val repository: ContactsRepository,
    private val mapper: ContactListDataToDomainMapper) {
    suspend fun fetchContacts():List<ContactDomain>{
        return mapper.map(repository.fetchContacts())
    }
}