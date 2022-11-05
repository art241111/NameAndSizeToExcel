package utils

import com.itextpdf.text.pdf.PdfReader
import java.io.File

fun getPageCount(path: String) = PdfReader(path).numberOfPages
fun getPageCount(file: File) = PdfReader(file.absolutePath).numberOfPages
