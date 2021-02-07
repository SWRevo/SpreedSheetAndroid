package id.indosw.spreedsheetandroid

object Configuration {
    private const val APP_SCRIPT_WEB_APP_URL =
        "https://script.google.com/macros/s/AKfycbyVQCEMs4FemB1spMo6a9PlNZbVv3nzCUD5O08tzvREthFEbOy1/exec"
    const val ADD_USER_URL = APP_SCRIPT_WEB_APP_URL
    const val LIST_USER_URL = "$APP_SCRIPT_WEB_APP_URL?action=readAll"
    const val KEY_ID = "uId"
    const val KEY_NAME = "uName"
    const val KEY_IMAGE = "uImage"
    const val KEY_ACTION = "action"
    const val KEY_USERS = "records"
}