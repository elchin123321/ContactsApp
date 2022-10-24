package com.ei.android.contactsapp.domain.contacts

class ContactsInteractor(
    private val repository: ContactsRepository,
    private val mapper: ContactListDataToDomainMapper) {
    suspend fun fetchContacts(onlyStarred:Boolean):List<ContactDomain>{
        return mapper.map(repository.fetchContacts(onlyStarred))
    }
    suspend fun changeStar(id:String,starred:Boolean):Boolean{
        return repository.changeStar(id,starred)
    }

}