package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.models.local.Character

class CharactersListScreenStateSlice: BaseListScreenStateSlice<Character>(
    EntityType.CHARACTERS,
)