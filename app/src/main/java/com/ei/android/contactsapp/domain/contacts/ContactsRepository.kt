package com.ei.android.contactsapp.domain.contacts

import com.ei.android.contactsapp.data.contacts.ContactData

interface ContactsRepository {
    suspend fun fetchContacts():List<ContactData>
}