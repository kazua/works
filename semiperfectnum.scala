
import scala.math._

object semiperfectnum {
    def getSemiPfnList(mn : Int) : Seq[Int] = {
        lazy val pr : Stream[Int] = 2 #:: Stream.from(3).filter(i => pr.takeWhile(j => pow(j, 2) <= i).forall(i % _ > 0)).takeWhile(_ <= sqrt(mn * 2))
        (1 to sqrt(mn * 2).toInt).map(i => pr.filter(_ % 2 != 0).takeWhile(_ < pow(2, i + 1)).map(j => (pow(2, i) * j).toInt).toList.map(k => (1 to (mn / k).toInt).map(n => k * n))).flatten.flatten.distinct.sortWith((a, b) => (a compareTo b) < 0)
    }
    def main(args : Array[String]) {
        val mn = 1000
        getSemiPfnList(mn).foreach(println)
    }
}