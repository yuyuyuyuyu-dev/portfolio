package dev.yuyuyuyuyu.portfolio.utils

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import dev.yuyuyuyuyu.portfolio.data.models.PortfolioItem

val PortfolioItem.displayName: String
    @Composable
    get() = nameRes?.let { stringResource(it) } ?: nameFallback ?: ""

val PortfolioItem.displayDescription: String
    @Composable
    get() = stringResource(descriptionRes)

val PortfolioItem.displayMotivation: String?
    @Composable
    get() = motivationRes?.let { stringResource(it) }
