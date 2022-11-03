// 1. Define you trait as a Service
trait Events: 
  def get(id: EventId): Task[Option[Event]]

// 2. Implement your service with a case class
case class EventsLive(logger: Logger, db: Database) extends Events:
  def get(id: EventId): Task[Option[Event]] = ???

// 3. Create a layer on the companion object
object Events:
  val live: URLayer[Logger & Database, EventsLive] =
    ZLayer.scoped {
      for 
        logger <- ZIO.service[Logger]
        db     <- ZIO.service[Database]
      yield EventsLive(logger, db)
    }