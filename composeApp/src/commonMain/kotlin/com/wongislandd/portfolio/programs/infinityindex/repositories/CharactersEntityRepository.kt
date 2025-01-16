package com.wongislandd.portfolio.programs.infinityindex.repositories

import com.wongislandd.portfolio.programs.infinityindex.models.local.Character
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkCharacter
import com.wongislandd.portfolio.programs.infinityindex.transformers.CharacterTransformer
import com.wongislandd.portfolio.programs.infinityindex.infra.networking.models.NetworkDataWrapper
import com.wongislandd.portfolio.programs.infinityindex.infra.paging.BaseRepository
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import io.ktor.client.HttpClient
import io.ktor.util.reflect.typeInfo

class CharactersEntityRepository(
    characterTransformer: CharacterTransformer,
    okHttpClient: HttpClient
) : BaseRepository<NetworkCharacter, Character>(
    characterTransformer,
    okHttpClient,
    EntityType.CHARACTERS,
    typeInfo<NetworkDataWrapper<NetworkCharacter>>()
)