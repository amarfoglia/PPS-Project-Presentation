val program: ZIO[Events, Throwable, Option[Event]] = 
  ZIO.serviceWithZIO[Events](_.get(4))