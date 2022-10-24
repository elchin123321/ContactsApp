package com.ei.android.contactsapp.data.contacts

import com.ei.android.contactsapp.domain.contacts.ContactsRepository

class BaseContactsRepository(
    private val contactsDataSource: ContactsDataSource,
) : ContactsRepository {
    override suspend fun fetchContacts(onlyStarred:Boolean): List<ContactData> {
        return contactsDataSource.fetchContacts(onlyStarred)
    }

    override suspend fun changeStar(id: String, starred:Boolean):Boolean {
        return contactsDataSource.changeStar(id,starred)
    }


}