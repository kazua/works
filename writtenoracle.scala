import scala.math._

object writtenOracle {

    def intCalcD(d : Double)(r : Double) : Int = floor((d + 1) * r).toInt
    val destinyp = intCalcD(_:Double)(random)

    def writtenOracle(name : String) : String = {
        val destinypoint = destinyp(100)
        destinypoint match {
            case 0 => name + "の運勢は大凶です。"
            case i if (i > 0 && i <= 3) => name + "の運勢は末凶です。"
            case i if (i > 3 && i <= 5) => name + "の運勢は半凶です。"
            case i if (i > 5 && i <= 10) => name + "の運勢は小凶です。"
            case i if (i > 10 && i <= 15) => name + "の運勢は凶です。"
            case i if (i > 15 && i <= 25) => name + "の運勢は末小吉です。"
            case i if (i > 25 && i <= 35) => name + "の運勢は末吉です。"
            case i if (i > 35 && i <= 50) => name + "の運勢は半吉です。"
            case i if (i > 50 && i <= 70) => name + "の運勢は吉です。"
            case i if (i > 70 && i <= 80) => name + "の運勢は小吉です。"
            case i if (i > 80 && i <= 95) => name + "の運勢は中吉です。"
            case i if (i > 95 && i <= 100) => name + "の運勢は大吉です。"
        }
    }

    def main(args : Array[String]) {
        lazy val name = if (args.size == 0 || args(0) == "") "guest" else args(0)
        println(writtenOracle(name))
    }
}