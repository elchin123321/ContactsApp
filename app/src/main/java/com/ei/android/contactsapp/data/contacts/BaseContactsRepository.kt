package com.ei.android.contactsapp.data.contacts

import com.ei.android.contactsapp.domain.contacts.ContactsRepository

class BaseContactsRepository(
    private val contactsDataSource: ContactsDataSource,
) : ContactsRepository {
    override suspend fun fetchContacts(): List<ContactData> {
        return contactsDataSource.fetchContacts()
    }
}