
import scala.math._

object perfectnum {
    def getPfnList(mn : Int) : Seq[BigInt] = {
        lazy val pr : Stream[Int] = 2 #:: Stream.from(3).filter(i => pr.takeWhile(j => pow(j, 2) <= i).forall(i % _ > 0)).takeWhile(_ <= sqrt(mn * 2))
        (1 to sqrt(mn * 2).toInt).map(i => (BigInt(1) << i) - 1).filter(pr.contains).map(j => j * (j + 1) / 2)
    }
    def main(args : Array[String]) {
        val mn = 100000000
        getPfnList(mn).foreach(println)
    }
}