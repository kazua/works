//write Kazua

import scala.collection.mutable._

object worldproblem9_1 {
    def CntUpStairs(sc : Int) = {
        sc + "段の階段の上り方は" + CntUpStairsProc(sc) + "通りあります。"
    }
    def CntUpStairsProc(sc : Int, ss : Map[Int, Int] = Map.empty) : Int = sc match {
        case c if c < 0 => 0
        case c if c == 0 => 1
        case c => ss match {
            case s if s.contains(c) => s(c)
            case s => {
                s(c) = (for (i <- 1 to 3) yield CntUpStairsProc(c - i, s)).sum
                s(c)
            }
        }
    }
    def main(args : Array[String]) {
        println(CntUpStairs(3))
        println(CntUpStairs(5))
        println(CntUpStairs(15))
        println(CntUpStairs(25))
        println(CntUpStairs(36))
    }
}