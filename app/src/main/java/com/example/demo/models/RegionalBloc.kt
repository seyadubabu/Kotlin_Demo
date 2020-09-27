package com.example.demo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class RegionalBloc {
    @SerializedName("acronym")
    @Expose
    private var acronym: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("otherAcronyms")
    @Expose
    private var otherAcronyms: List<Any?>? = null

    @SerializedName("otherNames")
    @Expose
    private var otherNames: List<Any?>? = null

    fun getAcronym(): String? {
        return acronym
    }

    fun setAcronym(acronym: String?) {
        this.acronym = acronym
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getOtherAcronyms(): List<Any?>? {
        return otherAcronyms
    }

    fun setOtherAcronyms(otherAcronyms: List<Any?>?) {
        this.otherAcronyms = otherAcronyms
    }

    fun getOtherNames(): List<Any?>? {
        return otherNames
    }

    fun setOtherNames(otherNames: List<Any?>?) {
        this.otherNames = otherNames
    }
}