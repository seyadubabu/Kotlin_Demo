package com.example.demo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Currency {
    @SerializedName("code")
    @Expose
    private var code: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("symbol")
    @Expose
    private var symbol: String? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getSymbol(): String? {
        return symbol
    }

    fun setSymbol(symbol: String?) {
        this.symbol = symbol
    }
}