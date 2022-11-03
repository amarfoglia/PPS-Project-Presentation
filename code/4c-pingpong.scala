for
  queue <- Queue.bounded[String](2)
  _     <- queue.offer("ping")
            .tap(_ => ZIO.debug("ping"))
            .forever
            .fork
yield ()