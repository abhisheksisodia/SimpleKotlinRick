package com.abhishek.rickmorty.repositories

import com.abhishek.networking.ApiOperation
import com.abhishek.networking.KtorClient
import com.abhishek.networking.models.domain.Character
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val ktorClient: KtorClient) {
    suspend fun fetchCharacter(characterId: Int): ApiOperation<Character> {
        return ktorClient.getCharacter(characterId)
    }
}