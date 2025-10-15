package com.navigation.impl.model

import com.navigation.api.Destination

sealed interface TestDestination : Destination{
    data object TestDestination1 : TestDestination
}

