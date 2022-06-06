package com.example.anifox.core

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val mainImmediate: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

class DefaultDispatchers(
    override val default: CoroutineDispatcher,
    override val io: CoroutineDispatcher,
    override val main: CoroutineDispatcher,
    override val mainImmediate: CoroutineDispatcher,
    override val unconfined: CoroutineDispatcher
) : DispatchersProvider

class TestDispatchers(
    override val default: CoroutineDispatcher,
    override val io: CoroutineDispatcher,
    override val main: CoroutineDispatcher,
    override val mainImmediate: CoroutineDispatcher,
    override val unconfined: CoroutineDispatcher
): DispatchersProvider