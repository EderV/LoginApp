package es.evm.exmpl.common.logger

interface Logger {

    fun d(tag: String, message: String)
    fun e(tag: String, message: String)
    fun i(tag: String, message: String)

}