package model.request

data class GetDeptsByParentIdRequest(
	val authId: Long = 0,
	val parentId: Long = 0,
	val baseRequest: BaseRequest = BaseRequest()
)