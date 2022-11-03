def increment(ref: Ref[Int]): UIO[Unit] =
  for
    n <- ref.get
    _ <- ref.set(n + 1)
  yield ()