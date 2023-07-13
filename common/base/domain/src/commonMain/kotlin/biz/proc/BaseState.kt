package biz.proc

import model.response.PageInfo

interface BaseState {
	val authId: Long
	val onStart: Boolean
	val isLoading: Boolean
	val pageInfo: PageInfo
}