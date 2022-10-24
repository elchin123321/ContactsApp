package com.ei.android.contactsapp.domain.contacts

import com.ei.android.contactsapp.data.contacts.ContactData

interface ContactsRepository {
    suspend fun fetchContacts(onlyStarred: Boolean): List<ContactData>
    suspend fun changeStar(id: String, starred: Boolean): Boolean

}