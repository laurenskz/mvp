package nl.pocketquest.data.DO

import com.google.gson.annotations.SerializedName

internal class Response<T>(
        @SerializedName("error") val isError: Boolean,
        val results: ArrayList<T>
)