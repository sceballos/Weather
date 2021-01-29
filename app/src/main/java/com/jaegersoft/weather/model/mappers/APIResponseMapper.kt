package com.jaegersoft.weather.model.mappers

import com.jaegersoft.weather.data.response.APIResponse
import com.jaegersoft.weather.model.entities.APIResponseNetworkEntity
import com.jaegersoft.weather.util.EntityMapper
import javax.inject.Inject

class APIResponseMapper
@Inject
constructor(): EntityMapper<APIResponseNetworkEntity, APIResponse>{
    override fun mapFromEntity(entity: APIResponseNetworkEntity): APIResponse {
        return APIResponse(entity.request, entity.location, entity.current, entity.forecast)
    }

    override fun mapToEntity(domainModel: APIResponse): APIResponseNetworkEntity {
        return APIResponseNetworkEntity(
            domainModel.request,
            domainModel.location,
            domainModel.current,
            domainModel.forecast)
    }

    override fun mapFromEntityList(entities: List<APIResponseNetworkEntity>): List<APIResponse> {
        return entities.map { mapFromEntity(it) }
    }
}