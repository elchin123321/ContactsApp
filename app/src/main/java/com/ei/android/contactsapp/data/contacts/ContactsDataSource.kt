package com.ei.android.contactsapp.data.contacts

import android.content.ContentValues
import android.content.Context
import android.provider.ContactsContract

class ContactsDataSource(private val context: Context) {
    private val idContact = ContactsContract.Contacts._ID
    private val lookupKey = ContactsContract.Contacts.LOOKUP_KEY
    private val name = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
    private val starred = ContactsContract.Contacts.STARRED
    private val photoUri = ContactsContract.Contacts.PHOTO_URI
    private val projection = arrayOf(
        idContact,
        lookupKey,
        name,
        starred,
        photoUri
    )
    private val contentResolver = context.contentResolver

    fun fetchContacts(onlyStared: Boolean): List<ContactData> {
        val contacts = mutableListOf<ContactData>()
        val selection = if (onlyStared) "$starred = ?" else null
        val selectionArgs = if (onlyStared) arrayOf(STARRED.toString()) else null
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )
        cursor?.let {
            while (cursor.moveToNext()) {
                try {
                    contacts.add(
                        ContactData(
                            cursor.getString(cursor.getColumnIndexOrThrow(idContact)),
                            cursor.getString(cursor.getColumnIndexOrThrow(lookupKey)),
                            cursor.getString(cursor.getColumnIndexOrThrow(name)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(starred)),
                            cursor.getString(cursor.getColumnIndexOrThrow(photoUri)),
                        )
                    )
                } catch (e: Exception) {

                }
            }
        }
        cursor?.close()
        return contacts
    }



    fun changeStar(id: String, starred: Boolean): Boolean {
        val contentValue = ContentValues()
        contentValue.put(ContactsContract.Contacts.STARRED, if (starred) UNSTARRED else STARRED)
        val selection = "$lookupKey = ?"
        val selectionArgs = arrayOf(id)
        val cursor = contentResolver.update(
            ContactsContract.Contacts.CONTENT_URI,
            contentValue,
            selection,
            selectionArgs,
        )
        return cursor != 0
    }

    companion object {
        private const val STARRED = 1
        private const val UNSTARRED = 0

    }


}