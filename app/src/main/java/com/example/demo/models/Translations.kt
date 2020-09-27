package com.example.demo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Translations {
    @SerializedName("de")
    @Expose
    private var de: String? = null

    @SerializedName("es")
    @Expose
    private var es: String? = null

    @SerializedName("fr")
    @Expose
    private var fr: String? = null

    @SerializedName("ja")
    @Expose
    private var ja: String? = null

    @SerializedName("it")
    @Expose
    private var it: String? = null

    @SerializedName("br")
    @Expose
    private var br: String? = null

    @SerializedName("pt")
    @Expose
    private var pt: String? = null

    @SerializedName("nl")
    @Expose
    private var nl: String? = null

    @SerializedName("hr")
    @Expose
    private var hr: String? = null

    @SerializedName("fa")
    @Expose
    private var fa: String? = null

    fun getDe(): String? {
        return de
    }

    fun setDe(de: String?) {
        this.de = de
    }

    fun getEs(): String? {
        return es
    }

    fun setEs(es: String?) {
        this.es = es
    }

    fun getFr(): String? {
        return fr
    }

    fun setFr(fr: String?) {
        this.fr = fr
    }

    fun getJa(): String? {
        return ja
    }

    fun setJa(ja: String?) {
        this.ja = ja
    }

    fun getIt(): String? {
        return it
    }

    fun setIt(it: String?) {
        this.it = it
    }

    fun getBr(): String? {
        return br
    }

    fun setBr(br: String?) {
        this.br = br
    }

    fun getPt(): String? {
        return pt
    }

    fun setPt(pt: String?) {
        this.pt = pt
    }

    fun getNl(): String? {
        return nl
    }

    fun setNl(nl: String?) {
        this.nl = nl
    }

    fun getHr(): String? {
        return hr
    }

    fun setHr(hr: String?) {
        this.hr = hr
    }

    fun getFa(): String? {
        return fa
    }

    fun setFa(fa: String?) {
        this.fa = fa
    }
}