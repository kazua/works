//http://projecteuler.net/index.php?section=problems&id=89(英語)
//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2089

//Kazua

import scala.io.Source

object problem89 {
    val p = "VIIII|LXXXX|DCCCC|IIII|XXXX|CCCC".r
    def problem89(ls : List[String]) = ls.mkString.length - ls.map(p.replaceAllIn(_, "--")).mkString.length
    def main(args : Array[String]) {
        println(problem89(Source.fromFile("e:/roman.txt").getLines.toList))
    }
}