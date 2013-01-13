//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2014
//K.A

object problem14 {
    def getCollatzDgt(nm : Long, acl : Int) : Int = nm match {
        case a if a == 1 => acl + 1
        case a => {
            val nn = if (nm % 2 == 0) nm / 2 else (3 * nm) + 1
            getCollatzDgt(nn, acl + 1)
        }
    }
    def getCollatzDgtMax(mn : Int) : Int = {
        (1 until mn).map(i => List(i, getCollatzDgt(i, 0))).reduceLeft((a, b) => if (a.tail.head > b.tail.head) a else b).head
    }
    def main(args : Array[String]) {
        val mn = 1000000
        println(getCollatzDgtMax(mn))
    }
}