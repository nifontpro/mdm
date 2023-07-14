package repo

import model.BaseEvent
import model.request.GetAllEventsRequest
import model.response.BaseResponse

interface EventRepository {
	suspend fun getEvents(request: GetAllEventsRequest): BaseResponse<List<BaseEvent>>
}