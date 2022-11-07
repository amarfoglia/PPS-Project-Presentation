val lines: ZStream[Any, Throwable, String] =
  ZStream.acquireReleaseWith(
    ZIO.attempt(Source.fromResource("stream_test.txt"))
  )(f => ZIO.succeed(f.close))
    .flatMap(f => ZStream.fromIterator(f.getLines))

val toInt: ZPipeline[Any, Nothing, String, Int] =
  ZPipeline.map[String, Int](_.toInt)

val greaterThanTwo: ZPipeline[Any, Nothing, Int, Int] =
  ZPipeline.filter[Int](_ > 2)

val appLogic: ZPipeline[Any, Nothing, String, Int] =
  toInt >>> greaterThanTwo

val program: ZIO[Any, Throwable, Int] =
  lines.via(appLogic).run(ZSink.sum[Int]) // 1,2,3,4 -> 3,4 -> 7