package excel

import data.FileData
import io.github.evanrupert.excelkt.workbook
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
                        cell("Размер, бит")
                        cell("Размер, КБ")
                        cell("Размер, МБ")
                    }
                    for (fileData in filesData)
                        row {
                            cell(fileData.name)
                            cell(fileData.size)
                            cell(fileData.size * 0.0009766)
                            cell(fileData.size * 9.5367431 * 10.0.pow(-7))
                        }
                }
            }.write(file.absolutePath)
        }
    }
}
