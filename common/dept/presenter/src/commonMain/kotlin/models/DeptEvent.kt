package models

sealed class DeptEvent {
    object AuthClicked : DeptEvent()
}