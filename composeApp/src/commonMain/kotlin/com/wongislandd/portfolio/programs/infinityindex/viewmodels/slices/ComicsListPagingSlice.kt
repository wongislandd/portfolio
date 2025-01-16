package com.wongislandd.portfolio.programs.infinityindex.viewmodels.slices

import com.wongislandd.portfolio.programs.infinityindex.infra.PagingBackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.util.EntityType
import com.wongislandd.portfolio.programs.infinityindex.infra.util.LookForwardDateHelper
import com.wongislandd.portfolio.programs.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.portfolio.programs.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.portfolio.programs.infinityindex.models.local.Comic
import com.wongislandd.portfolio.programs.infinityindex.models.network.NetworkComic
import com.wongislandd.portfolio.programs.infinityindex.repositories.CachingPreferenceRepository
import com.wongislandd.portfolio.programs.infinityindex.repositories.ComicsEntityRepository
import com.wongislandd.portfolio.programs.infinityindex.settings.NumberSetting
import com.wongislandd.portfolio.programs.infinityindex.settings.ToggleSetting
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class ComicsListPagingSlice(
    repository: ComicsEntityRepository,
    useCase: PagedListUseCase,
    private val cachingPreferenceRepository: CachingPreferenceRepository,
) : BaseListPagingSlice<NetworkComic, Comic>(
    repository, EntityType.COMICS, useCase
) {

    private var isDigitallyAvailableFilterEnabled: Boolean =
        cachingPreferenceRepository.getCachedBooleanPreference(
            ToggleSetting.DIGITALLY_AVAILABLE
        )
    private var isVariantsEnabled: Boolean = cachingPreferenceRepository.getCachedBooleanPreference(
        ToggleSetting.VARIANTS
    )

    private var lookAheadDateRange =
        LookForwardDateHelper.getLookForwardDateRange(
            cachingPreferenceRepository.getCachedNumberPreference(
                NumberSetting.LOOK_AHEAD_DAYS
            )
        )


    override fun afterInit() {
        super.afterInit()
        subscribeToSettingChanges()
    }

    private fun subscribeToSettingChanges() {
        sliceScope.launch {
            combine(
                cachingPreferenceRepository.getBooleanPreference(ToggleSetting.DIGITALLY_AVAILABLE),
                cachingPreferenceRepository.getBooleanPreference(ToggleSetting.VARIANTS),
                cachingPreferenceRepository.getIntPreference(NumberSetting.LOOK_AHEAD_DAYS)
            ) { isDigitallyAvailableEnabled, isVariantsEnabled, lookAheadDays ->
                Triple(isDigitallyAvailableEnabled, isVariantsEnabled, lookAheadDays)
            }.onEach { (isDigitallyAvailableEnabled, isVariantsEnabled, lookAheadDays) ->
                this@ComicsListPagingSlice.isDigitallyAvailableFilterEnabled =
                    isDigitallyAvailableEnabled
                this@ComicsListPagingSlice.isVariantsEnabled = isVariantsEnabled
                lookAheadDateRange = LookForwardDateHelper.getLookForwardDateRange(lookAheadDays)
                currentPagingSource?.invalidate()
            }.launchIn(this)
        }
    }

    override fun getAdditionalPagingParams(): Map<String, Any> {
        val extraParams: MutableMap<String, Any> = mutableMapOf(
            "dateRange" to lookAheadDateRange
        )
        if (isDigitallyAvailableFilterEnabled) {
            extraParams["hasDigitalIssue"] = true
        }
        if (!isVariantsEnabled) {
            extraParams["noVariants"] = true
        }
        return extraParams
    }

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        super.handleBackChannelEvent(event)
        when (event) {
            is PagingBackChannelEvent.SubmitDigitalAvailabilityFilterChange -> updatePagingParameters(
                digitalAvailabilityFilter = event.filterOn
            )

            is PagingBackChannelEvent.VariantsFilterChange -> updatePagingParameters(
                isVariantsEnabled = event.allowVariants
            )
        }
    }

    private fun updatePagingParameters(
        digitalAvailabilityFilter: Boolean? = null,
        isVariantsEnabled: Boolean? = null
    ) {
        var dataUpdateRequired = false
        if (digitalAvailabilityFilter != null && digitalAvailabilityFilter != isDigitallyAvailableFilterEnabled) {
            isDigitallyAvailableFilterEnabled = digitalAvailabilityFilter
            dataUpdateRequired = true
        }
        if (isVariantsEnabled != null && isVariantsEnabled != this.isVariantsEnabled) {
            this.isVariantsEnabled = isVariantsEnabled
            dataUpdateRequired = true
        }
        if (dataUpdateRequired) {
            currentPagingSource?.invalidate()
        }
    }

}