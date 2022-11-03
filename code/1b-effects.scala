val printRandomNumber = ZIO.attempt(
  println(s"Num: ${ScalaRandom.nextInt()}")
)
val printRandomNumberLater = printRandomNumber.delay(1.seconds)