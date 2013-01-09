//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2018
//K.A

object problem18 {
    val triangle = List(List(75),
                List(95, 64),
                List(17, 47, 82),
                List(18, 35, 87, 10),
                List(20, 04, 82, 47, 65),
                List(19, 01, 23, 75, 03, 34),
                List(88, 02, 77, 73, 07, 63, 67),
                List(99, 65, 04, 28, 06, 16, 70, 92),
                List(41, 41, 26, 56, 83, 40, 80, 70, 33),
                List(41, 48, 72, 33, 47, 32, 37, 16, 94, 29),
                List(53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14),
                List(70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57),
                List(91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48),
                List(63, 66, 04, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31),
                List(04, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 04, 23))

    def getTrgPerm(trg : List[List[Int]]) : Int = trg match {
        case a if a.size == 1 => a.head.max
        case a => getTrgPerm((a.init.last.zipWithIndex.map(m => m._1 + a.last.sliding(2).map(l => List(l.head,l.tail.head).max).toList(m._2)) :: a.init.init.reverse).reverse)
    }
    def main(args : Array[String]) {
        println(getTrgPerm(triangle))
    }
}