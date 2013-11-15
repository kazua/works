//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%20112
//write kazua

object problem112 {
    def ckBouncy(n : Int, k : Int = -1, z : Boolean = false, g : Boolean = false) : Boolean = k match {
        case k if k == 0 => (z && g)
        case k if k < 0 => ckBouncy(n % 10, n / 10)
        case k => {
            val x = k % 10
            if (x < n) ckBouncy(x, k / 10, true, g)
            else if (x > n) ckBouncy(x, k / 10, z, true)
            else if (z && g) ckBouncy(x, 0, z, g)
            else ckBouncy(x, k / 10, z, g)
        }
    }
    def problem112(i : Int = 538, b : Int = 269) : Int = (b.toDouble / i.toDouble * 100).toInt match {
        case n if n < 99 => if (ckBouncy(i)) problem112(i + 1, b + 1) else problem112(i + 1, b)
        case n => i
    }
    def main(args : Array[String]) {
        println(problem112())
    }
}