//write kazua

import scala.io._

object IslandSearch {
    def cntIsland(fp : String) : String = {
        val src = Source.fromFile(fp)("UTF-8")
        val lines = src.getLines.toList
        cntIslandProc(lines.tail, lines.head, 0, "", 0)
    }
    def cntIslandProc(src : List[String], nr : String, ni : Int, br : String, cnt : Int) : String = src match {
        case s if s == Nil && ni == nr.size => cnt + "の島があります。"
        case s => ni match {
            case i if i == nr.size => cntIslandProc(s.tail, s.head, 0, nr, cnt)
            case i if i == 0 =>
                if (nr(i).equals('●') && (br == "" || !br(i).equals('●')))
                    cntIslandProc(s, nr, i + 1, br, cnt + 1)
                else
                    cntIslandProc(s, nr, i + 1, br, cnt)
            case i =>
                if (nr(i).equals('●') && !nr(i - 1).equals('●') && (br == "" || !br(i).equals('●')))
                    cntIslandProc(s, nr, i + 1, br, cnt + 1)
                else
                    cntIslandProc(s, nr, i + 1, br, cnt)
        }
    }
    def main(args : Array[String]) {
        println(cntIsland("E:/island.txt"))
    }
}