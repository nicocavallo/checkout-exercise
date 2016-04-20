package exercise

import org.specs2.mutable.Specification

class CheckoutTest extends Specification {
  "Checkout" should {
    "charge" in {
      "£0 when array is empty" in {
        Checkout.buy(List[String]()) === 0.0
      }
      "£2.05 when input is [ Apple, Apple, Orange, Apple ] without offers" in {
        Checkout.buy(List("Apple", "Apple", "Orange", "Apple")) === 2.05
      }
    }
  }
}
