package com.wongislandd.portfolio.desktop

import com.wongislandd.portfolio.desktop.vm.DesktopScreenStateSlice
import com.wongislandd.portfolio.desktop.vm.FolderWidgetHandlerSlice
import com.wongislandd.portfolio.desktop.vm.InspectorScreenStateSlice
import com.wongislandd.portfolio.desktop.vm.LinkWidgetHandlerSlice
import com.wongislandd.portfolio.desktop.vm.ProgramWidgetManagementSlice
import com.wongislandd.portfolio.desktop.vm.TaskbarScreenStateSlice
import com.wongislandd.portfolio.desktop.vm.WidgetFinderScreenStateSlice
import com.wongislandd.portfolio.desktop.vm.WidgetProviderSlice
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val desktopModule = module {
    factoryOf(::DesktopScreenStateSlice)
    factoryOf(::TaskbarScreenStateSlice)
    factoryOf(::WidgetFinderScreenStateSlice)
    factoryOf(::InspectorScreenStateSlice)
    factoryOf(::WidgetProviderSlice)
    factoryOf(::ProgramWidgetManagementSlice)
    factoryOf(::FolderWidgetHandlerSlice)
    factoryOf(::LinkWidgetHandlerSlice)
}