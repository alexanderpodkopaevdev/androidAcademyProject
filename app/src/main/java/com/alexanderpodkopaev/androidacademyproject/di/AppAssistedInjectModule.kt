package com.alexanderpodkopaev.androidacademyproject.di

import com.squareup.inject.assisted.AssistedInject
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@Module(includes = [AssistedInject_AppAssistedInjectModule::class])
@AssistedModule
abstract class AppAssistedInjectModule {}