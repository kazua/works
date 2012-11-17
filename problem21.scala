//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2021
//K.A

object problem21 {
    def calcAmicable(xs : List[Int]) : Int = xs match {
        case Nil => 0
        case y :: ys => {
            val divisora = (Range(1,y + 1).toList.filter(y % _ == 0).sum - y)
            (if (Range(1, divisora + 1).toList.filter(divisora % _ == 0).sum - divisora == y && divisora != y) 1 else 0) + calcAmicable(ys)
        }
    }
    def problem21(is : Int) : Int = {
        val splLst = Range(1,is).toList.splitAt(is / 2)
        calcAmicable(splLst._1) + calcAmicable(splLst._2)
    }
    def main(args : Array[String]) {
        val problem21k = problem21 _
        println(problem21k(10000))
    }
}