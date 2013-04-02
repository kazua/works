//write Kazua

import scala.collection.mutable._

object worldproblem1_7 {
    def ZeroUpdate(tgt : Array[Array[Int]]) = {
        val rowbol = Map[Int, Int]()
        val colbol = Map[Int, Int]()

        for (i <- 0 until tgt.size) {
            rowbol(i) = 1
            for (j <- 0 until tgt(i).size) {
                if (tgt(i)(j) == 0) {
                    rowbol(i) = 0
                    colbol(j) = 0
                } else if (!colbol.contains(j)) {
                    colbol(j) = 1
                }
            }
        }
        for (i <- 0 until tgt.size) for (j <- 0 until tgt(i).size) if (rowbol(i) * colbol(j) == 0) tgt(i)(j) = 0
        tgt
    }
    def main(args : Array[String]) {
        ZeroUpdate(
            Array(
                Array(1, 4, 7, 9, 4, 5, 6, 3, 5, 6, 3, 1, 6, 8),
                Array(2, 3, 5, 7, 2, 4, 9, 0, 1, 2, 4, 5, 1, 9),
                Array(5, 3, 2, 7, 6, 1, 4, 1, 8, 9, 3, 2, 1, 6),
                Array(3, 3, 3, 1, 6, 8, 4, 2, 8, 4, 2, 4, 6, 3))).foreach(a => {
                a.foreach(print)
                println
            })
    }
}