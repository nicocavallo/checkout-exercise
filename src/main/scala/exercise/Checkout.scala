package exercise

object Checkout extends App {

  val price = buy(Option(args).map(_.toList).getOrElse(List()))
  println(s"The Price is £$price")

  def buy(products: List[String]) = products collect {
    case "Apple" => 0.6
    case "Orange" => 0.25
  } sum

}
