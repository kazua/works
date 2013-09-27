//write kazua

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.openxml4j.exceptions.InvalidFormatException
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.DataFormat
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import java.io._
import java.text._
import java.util.Date
import java.util.Calendar

class ExcelWorkBook {
    //ファイルタイプ列挙型
    object FileType extends Enumeration {
        val XLS, XLSX = Value
    }
    //セルタイプ列挙型
    object CellType extends Enumeration {
        val NUMERIC, STRING, ERROR, FORMULA, BLANK, BOOLEAN = Value
    }

    var workbk : Workbook = null // ワークブック
    var style : CellStyle = null // セルスタイル
    var dft : DataFormat = null // データフォーマット
    var filenm : String = "" // ファイル名
    var sheet : Sheet = null // ワークシート
    var cell : Cell = null // セル
    var row : Int = 0 // 行番号
    var col : Int = 0 // 列番号

    val numberPtn = "#0" // ナンバーパターン
    val datePtn = "yyyy/M/dd" // 日付パターン

    // ワークブック作成
    def createWorkbook(ft : FileType.Value = FileType.XLS) = ft match {
        case f if f == FileType.XLS => workbk = new HSSFWorkbook
        case f if f == FileType.XLSX => workbk = new XSSFWorkbook
    }

    def createCellStyle = style = workbk.createCellStyle //セルスタイル作成
    def getCellStyle = style // セルスタイル取得

    def createDataFormat = dft = workbk.createDataFormat // データフォーマット作成
    def getDataFormat = dft // データフォーマット取得

    def createSheet(sn : String) = sheet = workbk.createSheet(sn) // ワークシート作成
    def getSheet = sheet // ワークシート取得

    def getSheetName(sn : Int) = workbk.getSheetName(sn) // シート名取得
    def setSheetName(no : Int, name : String) = workbk.setSheetName(no, name) // シート名設定

    // セルタイプ取得
    def getCellType = {
        cell.getCellType match {
            case ct if ct == Cell.CELL_TYPE_NUMERIC => CellType.NUMERIC
            case ct if ct == Cell.CELL_TYPE_STRING => CellType.STRING
            case ct if ct == Cell.CELL_TYPE_FORMULA => {
                try {
                    cell.getErrorCellValue
                    CellType.ERROR
                } catch {
                    case ex : Exception => CellType.FORMULA
                }
            }
            case ct if ct == Cell.CELL_TYPE_BLANK => CellType.BLANK
            case ct if ct == Cell.CELL_TYPE_BOOLEAN => CellType.BOOLEAN
            case ct if ct == Cell.CELL_TYPE_ERROR => CellType.ERROR
        }
    }

    // ワークブック読み込み
    def bookRead(fn : String) : String = {
        try {
            filenm = fn
            val in = new FileInputStream(filenm)
            workbk = WorkbookFactory.create(in)
            selectSheet(0)
            "true"
        } catch {
            case ex : IOException => "ファイルが存在しない可能性があります。確認してください。" + ex.getStackTrace
            case ex : InvalidFormatException => "ファイルがEXCLE外である可能性があります。確認してください。" + ex.getStackTrace
            case ex : Exception => ex.getMessage + ex.getStackTrace
        }
    }
    // ワークブック保存
    def bookSave(fn : String = filenm) : String = {
        try {
            filenm = fn
            val out = new FileOutputStream(filenm)
            workbk.write(out)
            "true"
        } catch {
            case ex : IOException => "ファイルが存在しない可能性があります。確認してください。" + ex.getStackTrace
            case ex : InvalidFormatException => "ファイルがEXCLE外である可能性があります。確認してください。" + ex.getStackTrace
            case ex : Exception => ex.getMessage + ex.getStackTrace
        }
    }

    // ワークシートパスワード保護
    def protectSheet(pw : String) = sheet.protectSheet(pw)

    // シート選択（シート名）
    def selectSheet(name : String) = {
        sheet = workbk.getSheet(name)
        if (sheet == null) println("指定シート名が異常です")
    }
    // シート選択（シート番号）
    def selectSheet(no : Int) = no match {
        case no if no >= 0 && no < getNumberOfSheets => sheet = workbk.getSheetAt(no)
        case _ => println("指定シート番号が異常です")
    }

    // シート複製（シート名）
    def cloneSheet(name : String) : Unit = {
        val smp = getSheetNameMap
        val shn = smp.get(name)
        if (shn.isEmpty) cloneSheet(shn.get)
        else println("指定シート名が異常です")
    }
    // シート複製（シート番号）
    def cloneSheet(no : Int) : Unit = no match {
        case no if no >= 0 && no < getNumberOfSheets => sheet = workbk.cloneSheet(no)
        case _ => println("指定シート番号が異常です")
    }

    // シート削除（シート名）
    def removeSheet(name : String) : Unit = {
        val smp = getSheetNameMap
        val shn = smp.get(name)
        if (shn.isEmpty) removeSheet(shn.get)
        else println("指定シート名が異常です")
    }
    // シート削除（シート番号）
    def removeSheet(no : Int) : Unit = no match {
        case no if no >= 0 && no < getNumberOfSheets => workbk.removeSheetAt(no)
        case _ => println("指定シート番号が異常です")
    }

    // シート名Map取得（シート名,シート番号）
    def getSheetNameMap : scala.collection.mutable.Map[String, Int] = {
        val msn = getNumberOfSheets
        val smp = scala.collection.mutable.Map[String, Int]()
        for (i <- 0 until msn) smp += getSheetName(i) -> i
        smp
    }

    // 保持シート数取得
    def getNumberOfSheets = workbk.getNumberOfSheets

    // シート非表示状態取得（シート名）
    def isSheetHidden(name : String) : Boolean = {
        val smp = getSheetNameMap
        val shn = smp.get(name)
        if (shn.isEmpty) isSheetHidden(shn.get)
        else false
    }
    // シート非表示状態取得（シート番号）
    def isSheetHidden(no : Int) : Boolean = workbk.isSheetHidden(no)

    // シート非表示（マクロ）状態取得（シート名）
    def isSheetVeryHidden(name : String) : Boolean = {
        val smp = getSheetNameMap
        val shn = smp.get(name)
        if (shn.isEmpty) isSheetVeryHidden(shn.get)
        else false
    }
    // シート非表示（マクロ）状態取得（シート番号）
    def isSheetVeryHidden(no : Int) : Boolean = workbk.isSheetVeryHidden(no)

    // 行削除
    def removeRow(no : Int) = sheet.removeRow(sheet.getRow(no))

    // 行選択
    def selectRow(no : Int) = {
        row = no
        if (sheet.getRow(row) == null) createRow(row) else sheet.getRow(row)
    }
    // セル選択
    def selectCell(cno : Int, rno : Int) = {
        try {
            col = cno
            cell = selectRow(rno).getCell(cno)
            if (cell == null) selectRow(rno).createCell(cno)
        } catch {
            case ex : Exception => cell = null
        }
        this
    }

    // 最終行取得
    def getLastRowNum = sheet.getLastRowNum
    // 最終列取得
    def getLastCellNum(no : Int) = selectRow(no).getLastCellNum

    // 行作成
    def createRow(no : Int) = sheet.createRow(no)
    // 列作成
    def createCol(no : Int) = for (i <- 0 to getLastRowNum) selectRow(i).createCell(no)
    // セル作成
    def createCell(cno : Int, rno : Int) = {
        col = cno
        row = rno
        val rw = if (sheet.getRow(row) == null) createRow(row) else sheet.getRow(row)
        cell = rw.createCell(col)
        this
    }

    // 列コピー
    def copyCol(scn : Int, dcn : Int) : Unit = {
        if (scn == dcn) return
        for (i <- 0 to getLastRowNum) copyCell(scn, i, dcn, i)
        sheet.setColumnWidth(dcn, sheet.getColumnWidth(scn))
    }
    // 行コピー（同一シート）
    def copyRow(srn : Int, drn : Int) : Unit = {
        if (srn == drn) return
        for (i <- 0 until sheet.getRow(srn).getLastCellNum) copyCell(i, srn, i, drn)
        sheet.getRow(drn).setHeight(sheet.getRow(srn).getHeight)
    }
    // 行コピー（シート指定可）
    def copyRow(ssn : Int, srn : Int, dsn : Int, drn : Int) = {
        val sst = workbk.getSheetAt(ssn)
        val dst = workbk.getSheetAt(dsn)
        val srw = sst.getRow(srn)
        for (i <- 0 until srw.getLastCellNum) copyCell(ssn, i, srn, dsn, i, drn)
    }
    // セルコピー（同一シート）
    def copyCell(scn : Int, srn : Int, dcn : Int, drn : Int) : Unit = {
        selectCell(scn, srn)
        if (cell == null) return
        val ct = getCellType
        val scell = cell

        val drow = sheet.getRow(drn)
        if (drow == null) sheet.createRow(drn)
        cell = drow.getCell(dcn)
        if (cell == null) drow.createCell(dcn)

        ct match {
            case ct if ct == CellType.STRING => cell.setCellValue(scell.getRichStringCellValue)
            case ct if ct == CellType.NUMERIC => cell.setCellValue(scell.getNumericCellValue)
            case ct if ct == CellType.FORMULA => cell.setCellFormula(scell.getCellFormula)
            case ct if ct == CellType.BOOLEAN => cell.setCellValue(scell.getBooleanCellValue)
            case ct if ct == CellType.ERROR => cell.setCellValue(scell.getErrorCellValue)
        }

        cell.setCellStyle(scell.getCellStyle)
    }
    // セルコピー（シート指定可）
    def copyCell(ssn : Int, scn : Int, srn : Int, dsn : Int, dcn : Int, drn : Int) : Unit = {
        val dst = workbk.getSheetAt(dsn)

        selectSheet(ssn)
        selectCell(scn, srn)
        if (cell == null) return
        dst.setColumnWidth(dcn, sheet.getColumnWidth(scn))

        val ct = getCellType
        val scell = cell

        sheet = dst
        val drow = sheet.getRow(drn)
        if (drow == null) sheet.createRow(drn)
        cell = drow.getCell(dcn)
        if (cell == null) drow.createCell(dcn)

        ct match {
            case ct if ct == CellType.STRING => cell.setCellValue(scell.getRichStringCellValue)
            case ct if ct == CellType.NUMERIC => cell.setCellValue(scell.getNumericCellValue)
            case ct if ct == CellType.FORMULA => cell.setCellFormula(scell.getCellFormula)
            case ct if ct == CellType.BOOLEAN => cell.setCellValue(scell.getBooleanCellValue)
            case ct if ct == CellType.ERROR => cell.setCellValue(scell.getErrorCellValue)
        }

        cell.setCellStyle(scell.getCellStyle)
    }

    // セル値設定
    def setCellValue(value : Any) = value match {
        case v if v.isInstanceOf[String] => cell.setCellValue(v.asInstanceOf[String])
        case v if v.isInstanceOf[Boolean] => cell.setCellValue(v.asInstanceOf[Boolean])
        case v if v.isInstanceOf[Calendar] => cell.setCellValue(v.asInstanceOf[Calendar])
        case v if v.isInstanceOf[Date] => cell.setCellValue(v.asInstanceOf[Date])
        case v if v.isInstanceOf[Double] => cell.setCellValue(v.asInstanceOf[Double])
        case v if v.isInstanceOf[Int] => cell.setCellValue(v.asInstanceOf[Int])
    }
    // セル値取得
    def getCellValue : String = {
        if (cell == null) return ""
        val ct = getCellType

        ct match {
            case ct if ct == CellType.STRING => getStringCellValue
            case ct if ct == CellType.NUMERIC => {
                val format = new DecimalFormat(numberPtn)
                format.format(getNumericCellValue)
            }
            case ct if ct == CellType.FORMULA => {
                try {
                    cell.getErrorCellValue
                    ""
                } catch {
                    case ex : IllegalStateException => {
                        try {
                            val format = new DecimalFormat(numberPtn)
                            format.format(getNumericCellValue)
                        } catch {
                            case ex : IllegalStateException => {
                                try {
                                    getStringCellValue
                                } catch {
                                    case ex : IllegalStateException => ""
                                }
                            }
                        }
                    }
                }
            }
            case ct if ct == CellType.BOOLEAN => getBooleanCellValue.toString
            case ct if ct == CellType.ERROR => "#ERR"
            case ct if ct == CellType.BLANK => ""
        }
    }
    // セル値取得（数値）
    def getNumericCellValue : Double = {
        if (cell != null) {
            val ct = getCellType

            ct match {
                case ct if ct == CellType.NUMERIC || ct == CellType.FORMULA => cell.getNumericCellValue
                case ct if ct == CellType.STRING => {
                    try {
                        getStringCellValue.toDouble
                    } catch {
                        case ex : NumberFormatException => throw new IllegalStateException(ex)
                    }
                }
                case _ => 0
            }
        } else 0
    }
    // セル値取得（文字列）
    def getStringCellValue : String = {
        try {
            if (cell != null) cell.getRichStringCellValue.getString
            else ""
        } catch {
            case ex : IllegalStateException => ""
        }
    }
    //セル値取得（Boolean）
    def getBooleanCellValue : Boolean = if (cell != null) cell.getBooleanCellValue else false
    // セル値取得（日付）
    def getDateCellValue : Date = {
        if (cell != null) {
            try {
                cell.getDateCellValue()
            } catch {
                case ex : IllegalStateException => {
                    try {
                        val dft = new SimpleDateFormat(datePtn)
                        dft.parse(getStringCellValue)
                    } catch {
                        case ex : ParseException => null
                    }
                }
            }
        } else null
    }
    // セル値取得（数式）
    def getCellFormula : String = if (cell != null) cell.getCellFormula else ""

    // セル範囲アドレス文字列取得（etc, A1:B2）
    def getRangeAddress(scn : Int, srn : Int, ecn : Int, ern : Int) = new CellRangeAddress(srn, ern, ecn, scn).formatAsString
    // 現在セルアドレス取得
    def getCellAddress : String = getCellAddress(col, row)
    // 指定セルアドレス取得
    def getCellAddress(cno : Int, rno : Int) : String = getRangeAddress(cno, rno, cno, rno)

    // 行非表示（高さ0）
    def setZeroHeight(rno : Int, hdn : Boolean) = sheet.getRow(rno).setZeroHeight(hdn)
    // 行高さ設定
    def setRowHeight(rno : Int, het : Short) = sheet.getRow(rno).setHeight(het)
    // 列非表示
    def setColumnHidden(cno : Int, hdn : Boolean) = sheet.setColumnHidden(cno, hdn)
    // 列幅設定
    def setColumnWidth(cno : Int, wdh : Int) = sheet.setColumnWidth(cno, wdh)
}