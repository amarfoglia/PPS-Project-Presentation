def transfer(
  from: TRef[Int], to: TRef[Int], amount: Int
): STM[Throwable, Unit] =
  for
    senderBalance <- from.get
    _ <- if (amount > senderBalance)
          STM.fail(new Throwable("insufficient funds"))
         else
          from.update(_ - amount) *> to.update(_ + amount)
  yield ()