//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%20112
//write kazua

object problem112another {
    def ckBouncy(n : Int) = n.toString.toList.sortWith(_ < _).mkString.toInt != n && n.toString.toList.sortWith(_ > _).mkString.toInt != n
    def problem112(i : Int = 538, b : Int = 269) : Int = (b.toDouble / i.toDouble * 100).toInt match {
        case n if n < 99 => if (ckBouncy(i)) problem112(i + 1, b + 1) else problem112(i + 1, b)
        case n => i
    }
    def main(args : Array[String]) {
        println(problem112())
    }
}