//http://projecteuler.net/index.php?section=problems&id=80(英語)
//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2080

//Kazua

import java.math.BigDecimal
import java.math.RoundingMode

object problem80 {
    def sqrt(n : Int, s : Int, sr : BigDecimal = BigDecimal.valueOf(-1)) : BigDecimal = n match {
        case n if n <= 0 => BigDecimal.valueOf(0)
        case n => {
            val b = BigDecimal.valueOf(n)
            val sq : BigDecimal = if (sr.compareTo(BigDecimal.valueOf(0)) < 0) BigDecimal.valueOf(1).setScale(s + 3, java.math.RoundingMode.FLOOR) else sr
            val st : BigDecimal = if (sr.compareTo(BigDecimal.valueOf(0)) < 0) b else sq
            b.divide(st, s + 3, java.math.RoundingMode.FLOOR).add(st).divide(BigDecimal.valueOf(2), s + 3, java.math.RoundingMode.FLOOR) match {
                case a if a == st => a.setScale(s, java.math.RoundingMode.FLOOR)
                case a => sqrt(n, s, a)
            }
        }
    }
    def problem80 = (1 to 100).map(sqrt(_, 100)).filter(b => b.intValue != b.doubleValue).map(_.toString.replace(".", "").take(100).toList.map(_.toString.toInt).sum).sum
    def main(args : Array[String]) {
        println(problem80)
    }
}