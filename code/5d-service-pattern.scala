val run: ZIO[Any, Throwable, Option[Event]] =
  program.provide(Events.live, Logger.live, Database.live)
