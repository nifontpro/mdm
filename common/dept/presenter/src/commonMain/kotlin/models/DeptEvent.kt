package models

sealed class DeptEvent {
    object OnResume : DeptEvent()
}