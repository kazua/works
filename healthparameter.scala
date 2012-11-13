import scala.math._

object healthparameter {

    def healthparameter(name : String, years : Int, sex : String, height : Double, weight : Double) : List[String] = {
        lazy val heightm = if (height > 3.0) (height / 100.0) else height
        val bmi = weight / pow(heightm, 2)
        val sbw = pow(heightm, 2) * 22
        val rohrerindex = weight / pow(height, 3) * 10000000
        lazy val bmr = if (!sex.contains("f")) (13.397 * height) + (4.799 * height) - (5.677 * years) + 88.362
        else (9.247 * height) + (3.098 * height) - (4.33 * years) + 447.593

        ("名前：" + name) ::
            ("年齢：" + years) ::
            ("性別：" + sex) ::
            ("身長：" + height) ::
            ("体重：" + weight) ::
            ("BMI：" + bmi) ::
            ("適正体重：" + sbw) ::
            ("ローレル指数：" + rohrerindex) ::
            ("基礎代謝量：" + bmr) :: Nil
    }

    def main(args : Array[String]) {
        val name : String = args(0)
        lazy val years = try { args(1).toInt } catch { case ex : java.lang.NumberFormatException => { println("年齢指定が間違っています。指定年齢：" + args(1)); return ; } }
        lazy val sex = if (args(2).equalsIgnoreCase("f") ||
                           args(2).equalsIgnoreCase("m") ||
                           args(2).equalsIgnoreCase("female") ||
                           args(2).equalsIgnoreCase("male")) args(2).slice(0, 1)
        else { println("性別指定が間違っています。指定性別：" + args(2)); return ; }
        lazy val height = try { if (args(3).toDouble > 3.0) args(3).toDouble else args(3).toDouble * 100 } catch { case ex : java.lang.NumberFormatException => { println("身長指定が間違っています。指定身長：" + args(3)); return ; } }
        lazy val weight = try { args(4).toDouble } catch { case ex : java.lang.NumberFormatException => { println("体重指定が間違っています。指定体重:" + args(4)); return ; } }

        healthparameter(name, years, sex, height, weight).foreach(println)
    }
}