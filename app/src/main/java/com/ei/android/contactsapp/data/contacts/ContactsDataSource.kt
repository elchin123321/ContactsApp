package com.ei.android.contactsapp.data.contacts

import android.content.ContentResolver
import android.content.Context
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

     fun fetchContacts():List<ContactData> {
        val contacts = mutableListOf<ContactData>()
        val contentResolver = context.contentResolver
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            null,
            null,
            null
        )
        Log.d("SUPER",cursor?.columnCount.toString())
        cursor?.let {
            while (cursor.moveToNext()) {
                try{
                        contacts.add(
                            ContactData(
                                cursor.getString(cursor.getColumnIndexOrThrow(id)),
                                cursor.getString(cursor.getColumnIndexOrThrow(lookupKey)),
                                cursor.getString(cursor.getColumnIndexOrThrow(name)),
                                cursor.getInt(cursor.getColumnIndexOrThrow(starred)),
                                cursor.getString(cursor.getColumnIndexOrThrow(photoUri)),
                            )
                        )
                    Log.d("SUPER","DATA ADDED")
                }
                catch (e:Exception){
                    Log.d("SUPERSUPER",e.message.toString())

                }
            }
        }
        cursor?.close()
        return contacts
    }


}