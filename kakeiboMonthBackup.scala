import java.sql._
import java.io._
import scala.Array
 
object kakeiboMonthBackup {
    def using[A <: { def close() : Unit }, B](closable : A)(f : A => B) : B = try { f(closable) } finally { closable.close() }
    def main(args : Array[String]) {
        try {
            val year = args(0)
            val month = args(1)
 
            Class.forName("com.mysql.jdbc.Driver").newInstance;
            val con = DriverManager.getConnection("jdbc:mysql://192.168.*.*/***?user=***&password=***");
            try {
                val stmt = con.createStatement
                val rs = stmt.executeQuery("SELECT * FROM T_ACT where year = '" + year + "' and month = '" + month + "' order by year + month + day")
                val colcnt = rs.getMetaData.getColumnCount
                val dir = new File("/kakeibo/backup/" + year + month)
                if (!dir.exists) dir.mkdirs
                val file = new File(dir + "/backup.csv")
                val bwt = new BufferedWriter(new FileWriter(file))
                using(new PrintWriter(bwt)) { out =>
                    while (rs.next) {
                        for (i <- 1 until colcnt) out.print("|" + rs.getString(i) + "|,")
                        out.println("|" + rs.getString(colcnt) + "|")
                    }
                }
                stmt.close()
            } finally {
                con.close()
            }
        } catch {
            case e : ClassNotFoundException => println("使用できない定義があります" + e)
            case e : SQLException => println("DB処理でエラーが発生しました" + e)
            case e => e.printStackTrace
        }
    }
}