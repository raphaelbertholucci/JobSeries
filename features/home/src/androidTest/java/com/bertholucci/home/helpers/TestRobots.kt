package com.bertholucci.home.helpers

interface Setup<out EXECUTE : Execute<CHECK>, out CHECK : Check> {
    fun executeCreator(): EXECUTE
    fun checkCreator(): CHECK
    fun launch()

    infix fun execute(func: EXECUTE.() -> Unit): EXECUTE {
        launch()
        return executeCreator().apply(func)
    }
}

interface Execute<out CHECK : Check> {
    fun checkCreator(): CHECK
    infix fun check(func: CHECK.() -> Unit): CHECK {
        return checkCreator().apply(func)
    }
}

interface Check