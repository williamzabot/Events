package com.williamzabot.events.data.di

import com.williamzabot.events.data.repositories.CheckinRepositoryImpl
import com.williamzabot.events.data.repositories.EventRepositoryImpl
import com.williamzabot.events.domain.repositories.CheckinRepository
import com.williamzabot.events.domain.repositories.EventRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun injectEventRepository(
        eventRepositoryImpl: EventRepositoryImpl
    ): EventRepository

    @Singleton
    @Binds
    abstract fun injectCheckinRepository(
        checkinRepositoryImpl: CheckinRepositoryImpl
    ) : CheckinRepository

}