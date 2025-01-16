package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.portfolio.programs.infinityindex.models.local.Creator

class CreatorsListScreenStateSlice: BaseListScreenStateSlice<Creator>(
    EntityType.CREATORS
)