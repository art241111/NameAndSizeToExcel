package excel

import data.FileData
import io.github.evanrupert.excelkt.workbook
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import utils.replaceLastElement
import utils.toKbait
import utils.toMbait
import java.io.File
import kotlin.math.pow


class CreatorExcelReport(
    private val coroutineScope: CoroutineScope,
) {
    fun onSave(filesData: List<FileData>, file: File) {
        coroutineScope.launch(Dispatchers.IO) {
            workbook {
                sheet("Customers") {
                    row {
                        cell("Имя")
                        cell("Имя с заменой подчеркивания")
                        cell("Количество страниц")
//                        cell("Размер, бит")
                        cell("Размер, КБ")
//                        cell("Размер, МБ")
                    }
                    for (fileData in filesData)
                        row {
                            with(fileData) {
                                cell(name)
                                cell(name.replaceLastElement('_', '/'))
                                cell(pageCount)
//                                cell(size)
                                cell(size.toKbait())
//                                cell(size.toMbait())
                            }
                        }
                }
            }.write(file.absolutePath)
        }
    }
}
