package exercise

import org.specs2.mutable.Specification

class CheckoutTest extends Specification with Checkout{
  "Checkout" should {
    "charge" in {
      "£0 when array is empty" in {
        buy(new Basket()) === 0.0
      }
      "£2.05 when input is [ Apple, Apple, Orange, Apple ] without offers" in {
        buy(new Basket(Seq("Apple", "Apple", "Orange", "Apple"))) === 2.05
      }
      "£1.45 when input is [ Apple, Apple, Orange, Apple ] with offers" in {
        buy(new Basket(Seq("Apple", "Apple", "Orange", "Apple")),Seq(Offers.buyOneAppleGetOneFree)) === 1.45
      }
      "£0.50 when input is [ Orange, Orange, Orange ] with offers" in {
        buy(new Basket(Seq("Orange","Orange", "Orange")),Seq(Offers.buyTwoOrangesGetOneFree)) === 0.5
      }
      "combine different offers" in {
        buy(new Basket(Seq("Apple", "Apple", "Orange","Orange", "Orange", "Apple")),
          Seq(Offers.buyTwoOrangesGetOneFree,Offers.buyOneAppleGetOneFree)) === 1.70
      }
    }
  }
}
