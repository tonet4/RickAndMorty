package com.example.rickandmorty.domain.exception

class DomainException(
    val error: DomainError
) : Exception()