package dev.yuyuyuyuyu.portfolio

import kotlinx.browser.document

actual fun setWindowTitle(title: String) {
    document.title = title
}
