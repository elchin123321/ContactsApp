package com.ei.android.contactsapp.domain.contacts

class ContactsInteractor(
    private val repository: ContactsRepository,
    private val mapper: ContactListDataToDomainMapper) {
    suspend fun fetchContacts():List<ContactDomain>{
        return mapper.map(repository.fetchContacts())
    }
}