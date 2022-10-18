package com.ei.android.contactsapp.core

interface Mapper {
    interface Data<S, R> {
        fun map(source: S): R
    }
}