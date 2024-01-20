package es.evm.exmpl.common

class Progress(val status: ProgressStatus, val message: String) {

    companion object {
        fun show(message: String) = Progress(ProgressStatus.SHOW, message)
        fun show() = show("")
        fun hide() = Progress(ProgressStatus.HIDE, "")
    }

    enum class ProgressStatus {
        SHOW, HIDE
    }
}
