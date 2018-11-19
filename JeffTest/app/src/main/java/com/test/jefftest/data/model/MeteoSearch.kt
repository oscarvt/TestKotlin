package com.test.jefftest.data.model

import com.test.jefftest.data.cache.Config

data class MeteoSearch(
        val coordenates: Coordenates,
        val stype: String = "FULL",
        val username: String = Config.usernameMeteo)