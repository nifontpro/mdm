package repo

import constants.CLIENT_URL
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import model.BaseEvent
import model.request.GetAllEventsRequest
import model.response.BaseResponse

class EventRepositoryImpl(
	private val httpClient: HttpClient
) : EventRepository {

	override suspend fun getEvents(request: GetAllEventsRequest): BaseResponse<List<BaseEvent>> {
		return httpClient.post(eventUrl("get_all")) {
			setBody(request)
		}.body()
	}

	companion object {
		private fun eventUrl(patch: String) = "$CLIENT_URL/event/$patch"
	}
}