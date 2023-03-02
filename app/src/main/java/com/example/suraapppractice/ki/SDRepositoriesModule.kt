package com.example.suraapppractice.ki

import com.example.suraapppractice.flows.dashboard.repositories.SDMovementsRepository
import com.example.suraapppractice.flows.login.repositories.SDLoginRepository
import org.koin.dsl.module

val sdRepositoriesModule = module {
    factory { SDLoginRepository(get()) }
    factory { SDMovementsRepository(get()) }
}