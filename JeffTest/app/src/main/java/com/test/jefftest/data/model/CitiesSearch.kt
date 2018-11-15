package com.test.jefftest.data.model

import com.test.jefftest.data.cache.Config

data class CitiesSearch(var q: String,
                        val maxRows: Int = 20,
                        val startRow: Int = 20,
                        val lang: String = "en",
                        val isNameRequired: Boolean = true,
                        val stype: String = "FULL",
                        val username: String = Config.usernameMeteo)