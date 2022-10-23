package com.ei.android.contactsapp.data.contacts

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import java.lang.Exception
import java.lang.RuntimeException

class ContactsDataSource(private val context: Context) {
    private val id = ContactsContract.Contacts._ID
    private val lookupKey = ContactsContract.Contacts.LOOKUP_KEY
    private val name = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
    private val starred = ContactsContract.Contacts.STARRED
    private val photoUri = ContactsContract.Contacts.PHOTO_URI
    private val projection = arrayOf(
        id,
        lookupKey,
        name,
        starred,
        photoUri
    )

    fun fetchContacts(filter: String = ""): List<ContactData> {
        val filterUri = Uri.parse(ContactsContract.Contacts.CONTENT_FILTER_URI.toString())
        val contacts = mutableListOf<ContactData>()
        val contentResolver = context.contentResolver
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            null,
            null,
            null
        )
        cursor?.let {
            while (cursor.moveToNext()) {
                try {
                    contacts.add(
                        ContactData(
                            cursor.getString(cursor.getColumnIndexOrThrow(id)),
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


}