val program = ZIO.succeed(println("Hello, World!"))
              // .uninterruptible
for
  fiber <- program.fork
  _     <- fiber.interrupt
yield ()