package com.example.anifox.core.enums

import android.content.Context
import android.os.Parcelable
import com.example.anifox.R
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class GenreConstants(private val stringResource: Int) : Parcelable {
    Comedy(R.string.Genre_Comedy),
    Romance(R.string.Genre_Romance),
    Fantasy(R.string.Genre_Fantasy),
    Horror(R.string.Genre_Horror),
    Mystic(R.string.Genre_Mystic),
    Drama(R.string.Genre_Drama),
    Adventure(R.string.Genre_Adventure),
    Mechanic(R.string.Genre_Mechanic),
    Seinen(R.string.Genre_Seinen),
    Senen(R.string.Genre_Senen),
    School(R.string.Genre_School),
    Demons(R.string.Genre_Demons);

    fun toReadableName(context: Context): String = context.resources.getString(this.stringResource)
    fun toReadableNameAll(context: Context): List<String> = listOf(
            context.resources.getString(Comedy.stringResource),
            context.resources.getString(Romance.stringResource),
            context.resources.getString(Fantasy.stringResource),
            context.resources.getString(Horror.stringResource),
            context.resources.getString(Mystic.stringResource),
            context.resources.getString(Drama.stringResource),
            context.resources.getString(Adventure.stringResource),
            context.resources.getString(Mechanic.stringResource),
            context.resources.getString(Seinen.stringResource),
            context.resources.getString(Senen.stringResource),
            context.resources.getString(School.stringResource),
            context.resources.getString(Demons.stringResource),
        )
}