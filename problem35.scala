//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2035
//K.A

object problem35 {
    lazy val pr : Stream[Int] = 2 #:: Stream.from(3).filter(i => pr.takeWhile(j => BigInt(j).pow(2) <= i).forall(i % _ > 0)).takeWhile(_ <= 1000000)
    def isPrime(i : Int) = {
        if (i < 2) false
        else (2 until i).exists(i % _ == 0) == false
    }
    def problem35 = pr.filter(i => (List(0, 2, 4, 5, 6, 8).forall(j => i.toString.indexOf(j.toString) == -1) || i.toString.length == 1) && (1 until i.toString.length).map(n => i.toString.drop(n) + i.toString.take(n)).forall(k => isPrime(k.toInt) == true)).size
    def main(args : Array[String]) {
        println(problem35)
    }
}