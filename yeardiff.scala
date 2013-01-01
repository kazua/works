//K.A
//年の中に同じ数値が現れない年のリスト

object yeardiff {
    def yeardiff(maxyear: Int): List[String] = {
        (1 to maxyear).map(_.toString.toArray).filter(a => a.distinct.size == a.size).map(_.mkString).toList
    }
    def main(args: Array[String]) {
        val nowyear = 2013
        yeardiff(nowyear).foreach(println)
    }
}