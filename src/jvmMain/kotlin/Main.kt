// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import data.FileData
import data.Сondition
import excel.CreatorExcelReport
import kotlinx.coroutines.CoroutineScope
import ui.views.fileManager.Dialog
import ui.views.fileManager.DialogFile
import utils.getPageCount
import javax.swing.filechooser.FileNameExtensionFilter

@Composable
@Preview
fun App(frameScope: FrameWindowScope) {
    var condition by remember { mutableStateOf(Сondition.DEFAULT) }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    var fileData by remember { mutableStateOf<List<FileData>>(emptyList()) }
    val creatorExcelReport by remember { mutableStateOf(CreatorExcelReport(coroutineScope)) }

    MaterialTheme {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            MainScreen(
                condition,
                onLoadFile = {
                    condition = Сondition.LOADING_FILES
                },
                onSaveToExcel = {
                    condition = Сondition.SAVING
                },
                filesData = fileData
            )
        }

        if (condition == Сondition.LOADING_FILES) {
            DialogFile(
                coroutineScope = coroutineScope,
                scope = frameScope,
                extensions = listOf(FileNameExtensionFilter("Pdf file", "pdf")),
                onResult = { files ->
                    if (!files.isEmpty()) {
                        val filesData = mutableListOf<FileData>()
                        files.forEach { file ->
                            filesData.add(
                                FileData(
                                    name = file.name,
                                    size = file.length(),
                                    pageCount = getPageCount(file)
                                )
                            )
                        }
                        fileData = filesData
                        condition = Сondition.HAVE_FILES_AT_MEMORY
                    }
                }
            )
        }

        if (condition == Сondition.SAVING) {
            DialogFile(
                coroutineScope = coroutineScope,
                mode = Dialog.Mode.SAVE,
                scope = frameScope,
                extensions = listOf(FileNameExtensionFilter("Excel file", "xlsx")),
                onResult = {
                    creatorExcelReport.onSave(fileData, it[0])
                    condition = Сondition.HAVE_FILES_AT_MEMORY
                }
            )
        }

    }
}

fun main() = application {
    Window(
        title = "Имя и размер в ексель",
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(
            width = 400.dp,
            height = 250.dp,
            position = WindowPosition(alignment = Alignment.Center)
        ),
    ) {
        App(
            frameScope = this
        )

    }
}
