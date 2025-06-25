package com.kxmillx.focusmate.di

import com.kxmillx.focusmate.data.repository.NoteRepositoryImpl
import com.kxmillx.focusmate.data.repository.TaskRepositoryImpl
import com.kxmillx.focusmate.domain.repository.NoteRepository
import com.kxmillx.focusmate.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTaskRepository(impl: TaskRepositoryImpl): TaskRepository = impl

    @Provides
    @Singleton
    fun provideNoteRepository(impl: NoteRepositoryImpl): NoteRepository = impl
}