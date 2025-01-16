package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.models.local.Character
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkCharacter
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.portfolio.programs.infinityindex.repositories.CharactersEntityRepository

class AllCharactersPagingSlice(
    repository: CharactersEntityRepository
): BaseListPagingSlice<NetworkCharacter, Character>(
    repository,
    EntityType.CHARACTERS,
    PagedListUseCase.ALL_AVAILABLE
)