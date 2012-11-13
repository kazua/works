import scala.math._

object writtenOracle {

    def destiny(d : Double)(r : Double) : Int = floor(d * r).toInt

    val destinyp = destiny(12)_

    def writtenOracle(name : String) : String = {
        val destinypoint = destinyp(random)
        destinypoint match {
            case 1 => name + "の運勢は大凶です。"
            case 2 => name + "の運勢は末凶です。"
            case 3 => name + "の運勢は半凶です。"
            case 4 => name + "の運勢は小凶です。"
            case 5 => name + "の運勢は凶です。"
            case 6 => name + "の運勢は末小吉です。"
            case 7 => name + "の運勢は末吉です。"
            case 8 => name + "の運勢は半吉です。"
            case 9 => name + "の運勢は吉です。"
            case 10 => name + "の運勢は小吉です。"
            case 11 => name + "の運勢は中吉です。"
            case 12 => name + "の運勢は大吉です。"
        }
    }

    def main(args : Array[String]) {
        lazy val name = if (args.size == 0 || args(0) == "") "guest" else args(0)
        println(writtenOracle(name))
    }
}