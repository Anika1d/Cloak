package com.clicklead.cloak.core.usecase

import com.clicklead.cloak.core.data.request.InstallTypeDto
import com.clicklead.cloak.core.repository.api.UrlRepositoryI

class GetUrlUseCase(private val repository: UrlRepositoryI) {
    suspend operator fun invoke(installTypeDto: InstallTypeDto) =
        repository.getUrl(installTypeDto = installTypeDto)
}