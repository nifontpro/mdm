package models

sealed class DeptEvent {
    object OnTest : DeptEvent()
    object OnResume : DeptEvent()
}