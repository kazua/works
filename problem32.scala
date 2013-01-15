//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2032
//K.A

object problem32 {
    def problem32 = (1 to 99).map(i => if (i < 10) (1000 to 9999).map(j => List(i, j, (i * j)))
                                             else (100 to 999).map(j => List(i, j, (i * j)))
                                 ).flatten.filter(_.mkString.length == 9)
                                 .filter(k => (1 to 9).map(m => k.mkString.indexOf(m.toString)).forall(n => n >= 0))
                                 .map(_.last).distinct.sum
    def main(args : Array[String]) {
        println(problem32)
    }
}