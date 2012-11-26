//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%202
//K.A

object problem02 {
    def fbnEvnSum(mn : Int) : BigInt = {
        lazy val fbn: Stream[Int] = 0 #:: 1 #:: fbn.zip(fbn.tail).map(i => i._1 + i._2).takeWhile(_ <= mn)
        fbn.filter(_ % 2 == 0).sum
    }
    def main(args : Array[String]) {
        val mn = 4000000
        println(fbnEvnSum(mn))
    }
}