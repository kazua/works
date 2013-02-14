import java.sql._
import java.io._
import scala.Array
import com.itextpdf.text._
import com.itextpdf.text.pdf._
import com.itextpdf.text.pdf.fonts._
import com.itextpdf.text.pdf.BaseFont._

object kakeiboPDFPrint {
    def main(args : Array[String]) {
        try {
            val stday = args(0)
            val edday = args(1)

            Class.forName("com.mysql.jdbc.Driver").newInstance
            val con = DriverManager.getConnection("jdbc:mysql://192.168.*.*/***?user=***&password=***")
            try {
                val stmt = con.createStatement
                val sql = "select " +
                    "  *  " +
                    "from " +
                    "  (  " +
                    "    select " +
                    "      A.actcd " +
                    "      , concat(A.year, '/', A.month, '/', A.day) as ymd " +
                    "      , S.sbnm " +
                    "      , A.itemnm " +
                    "      , A.yen " +
                    "      , A.lastupday " +
                    "      , U.usrnm  " +
                    "    from " +
                    "      T_ACT A  " +
                    "      inner join M_SBK S  " +
                    "        ON A.sbcd = S.sbcd  " +
                    "      inner join M_USR U  " +
                    "        ON A.lastupus = U.usrid  " +
                    "    where " +
                    "      concat(A.year,A.month,A.day) >= '" + stday + "' " +
                    "      and concat(A.year,A.month,A.day) <= '" + edday + "' " +
                    "      and A.delflg = '0'  " +
                    "    union  " +
                    "    select " +
                    "      '-' " +
                    "      , concat(  " +
                    "        max(concat(A.year, '/', A.month, '/', A.day)) " +
                    "        , '時点' " +
                    "      )  " +
                    "      , '-' " +
                    "      , '合計' " +
                    "      , sum(A.yen) " +
                    "      , '-' " +
                    "      , '-'  " +
                    "    from " +
                    "      T_ACT A  " +
                    "    where " +
                    "      concat(A.year,A.month,A.day) >= '" + stday + "' " +
                    "      and concat(A.year,A.month,A.day) <= '" + edday + "' " +
                    "      and A.delflg = '0' " +
                    "  ) A  " +
                    "order by " +
                    "  A.ymd " +
                    "  , A.lastupday "

                val rs = stmt.executeQuery(sql)
                val colcnt = rs.getMetaData.getColumnCount
                val dir = new File("/data/pdf/temp")
                if (!dir.exists) dir.mkdirs
                val file = new File(dir + "/" + stday + edday + ".pdf")
                val bos = new BufferedOutputStream(new FileOutputStream(file, false))
                val doc = new Document(PageSize.A4, 50, 20, 10, 10)
                val pw = PdfWriter.getInstance(doc, bos)
                val font = new Font(BaseFont.createFont("HeiseiMin-W3", "UniJIS-UCS2-HW-H", false), 10, Font.BOLD)
                font.setColor(0, 0, 0)
                doc.open

                doc.add(new Paragraph(stday.substring(0, 4) + "/" + stday.substring(4, 6) + "/" + stday.substring(6, 8)
                    + "～" + edday.substring(0, 4) + "/" + edday.substring(4, 6) + "/" + edday.substring(6, 8) + "家計支出一覧", font))
                doc.add(new Paragraph(" "))
                val tbl = new PdfPTable(colcnt)
                val TableWidth = Array(50F, 100F, 120F, 240F, 70F, 100F, 80F)
                tbl.setWidthPercentage(100)
                tbl.setWidths(TableWidth)
                tbl.getDefaultCell.setBackgroundColor(BaseColor.BLUE);
                tbl.addCell(new Phrase("No.", font))
                tbl.addCell(new Phrase("支出日", font))
                tbl.addCell(new Phrase("種別", font))
                tbl.addCell(new Phrase("商品名", font))
                tbl.addCell(new Phrase("金額", font))
                tbl.addCell(new Phrase("登録日", font))
                tbl.addCell(new Phrase("登録者", font))
                tbl.getDefaultCell.setGrayFill(0.7f)
                tbl.setHeaderRows(1)
                tbl.getDefaultCell.setBackgroundColor(BaseColor.WHITE);
                while (rs.next) for (i <- 1 to colcnt) tbl.addCell(new Phrase(rs.getString(i), font))
                doc.add(tbl)

                doc.close
                stmt.close
            } catch {
                case e => throw e
            } finally {
                con.close
            }
        } catch {
            case e : ClassNotFoundException => println("使用できない定義があります" + e)
            case e : SQLException => println("DB処理でエラーが発生しました" + e)
            case e => e.printStackTrace
        }
    }
}