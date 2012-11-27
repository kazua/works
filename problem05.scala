//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%205
//K.A

object problem05 {
    def getDivMin(sl : List[Int]) : Int = {
        Range(sl.last, Int.MaxValue).takeWhile(i=> !sl.forall(i % _ == 0)).max + 1
    }
    def main(args : Array[String]) {
        val sl = (1 to 20).toList
        println(getDivMin(sl))
    }
}