//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2039
//K.A

object problem39 {
    def problem39 = (3 to 1000).map(i => for (b <- 2 until i; a <- 1 until b; c = i - a - b if a * a + b * b == c * c && c > 0) yield List(i, a, b, c)).filter(_ != Nil).map(n => List(n.head.head, n.size)).sortBy(s => s.last).last.head
    def main(args : Array[String]) {
        println(problem39)
    }
}