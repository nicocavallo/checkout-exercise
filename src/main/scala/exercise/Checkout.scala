package exercise

trait Checkout {

  val prices = Map(Apple -> BigDecimal("0.6"), Orange -> BigDecimal("0.25")).withDefaultValue(BigDecimal("0.0"))

  type Offer = Seq[Item] => Seq[Item]

  def buy(basket:Basket, offersToApply: Seq[Offer] = Seq(Offers.noOffers)): BigDecimal = {
    (basket.items map prices sum) - (offersToApply.flatMap{offer =>
      offer(basket.items)} map prices sum)
  }

}

object Offers {

  import CheckoutUtils._

  def noOffers(items:Seq[Item]) = Seq()
  def buyOneAppleGetOneFree(items:Seq[Item]) = half(items collect {
    case Apple => Apple
  })
  def buyTwoOrangesGetOneFree(items:Seq[Item]) = split(items collect {
    case Orange => Orange
  }, 1, 3)

}

object CheckoutUtils {
  def half[T](seq:Seq[T]) = seq.take(seq.length/2)
  def split[T](seq:Seq[T], num: Int, denom: Int) = seq.take(seq.length*num / denom)
}

object CheckoutApp extends App with Checkout{

  val basket = new Basket(Option(args).map(_.toSeq).getOrElse(Seq()))

  val price = buy(basket,Seq(Offers.buyTwoOrangesGetOneFree,Offers.buyOneAppleGetOneFree))
  println(s"The Price is £$price")

}

class Basket(in:Seq[String] = Seq()) {
  val items = in collect {
    case "Apple" => Apple
    case "Orange" => Orange
  }
}

sealed trait Item
object Apple extends Item
object Orange extends Item