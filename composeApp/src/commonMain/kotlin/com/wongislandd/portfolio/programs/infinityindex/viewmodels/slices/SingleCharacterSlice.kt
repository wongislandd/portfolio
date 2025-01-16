package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.models.local.Character
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkCharacter
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseSingleEntityResolutionSlice
import com.wongislandd.portfolio.programs.infinityindex.repositories.CharactersEntityRepository

class SingleCharacterSlice(
    charactersRepository: CharactersEntityRepository
): BaseSingleEntityResolutionSlice<NetworkCharacter, Character>(
    EntityType.CHARACTERS,
    charactersRepository
)