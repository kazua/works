//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2023
//K.A

object problem23 {
    def getNoAbdNum(mn : Int) : Int = {
        val an = (0 to mn).map(i => Range(1, i).filter(i % _ == 0).sum).zipWithIndex.filter(j => j._1 > j._2).map(_._2)
        val on = an.flatMap(i => an.takeWhile(_ <= mn).map(_ + i))

        (1 to mn).diff(on).sum
    }
    def main(args : Array[String]) {
        val mn = 28123
        println(getNoAbdNum(mn))
    }
}