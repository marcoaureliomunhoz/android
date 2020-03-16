package br.com.marcoaureliomunhoz.biblio.repositories

class Resource<T> (
    val data: T,
    val messages: String? = null
) {
}