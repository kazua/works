//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2042
//write kazua

import scala.io.Source
import scala.math._

object problem42 {
    def isTriNum(n : Double) = (sqrt(8 * n + 1.0) - 1.0) / 2.0 == ((sqrt(8 * n + 1.0) - 1.0) / 2.0).toInt
    def alpindex(c : Char) = "abcdefghijklmnopqrstuvwxyz".indexOf(c.toLower) + 1
    def problem42(fp : String) = {
        val s = Source.fromFile(fp)("UTF-8").getLines.mkString.split(",")
        s.map(_.toList.map(alpindex).sum.toDouble).map(isTriNum).filter(_ == true).size
    }
    def main(args : Array[String]) {
        println(problem42("E:\\words.txt"))
    }
}