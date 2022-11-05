package views

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import data.FileData
import utils.replaceLastElement

@Composable
fun DataTable(
    filesData: List<FileData>
) {
    LazyVerticalGrid(GridCells.Fixed(6)) {
        filesData.forEach {
            item {
                DataTableItem(it.name)
            }

            item {
                DataTableItem(it.name.replaceLastElement('_', '/'))
            }

            item {
                DataTableItem(it.size.toString())
            }

            item {
                DataTableItem(it.size.toString())
            }
            item {
                DataTableItem(it.size.toString())
            }
            item {
                DataTableItem(it.size.toString())
            }
        }
    }
}