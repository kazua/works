//K.A

object BetrothedNum {
    def getBetrothedNum(min : Int, max : Int) = (min to max).map(i => List(i, (2 until i).filter(i % _ == 0).sum)).map(j => List(j.head, j.tail.head, (2 until j.tail.head).filter(j.tail.head % _ == 0).sum)).filter(k => k.head == k.tail.tail.head).map(k => List(k.head, k.tail.head)).filter(n => n.head < n.tail.head)
    def main(args : Array[String]) {
        val min = 1
        val max = 10000
        getBetrothedNum(min, max).foreach(println)
    }
}