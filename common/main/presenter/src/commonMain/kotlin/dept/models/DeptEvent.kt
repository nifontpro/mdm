package dept.models

sealed class DeptEvent {
    object AuthClicked : DeptEvent()
}