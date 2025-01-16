package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.models.local.Creator
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkCreator
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseSingleEntityResolutionSlice
import com.wongislandd.portfolio.programs.infinityindex.repositories.CreatorsEntityRepository

class SingleCreatorSlice(
    creatorRepository: CreatorsEntityRepository
): BaseSingleEntityResolutionSlice<NetworkCreator, Creator>(
    EntityType.CREATORS,
    creatorRepository
)