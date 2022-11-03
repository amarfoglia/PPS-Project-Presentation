val printRandomNumber: Unit = println(s"Num: ${Random.nextInt()}")
val scheduler = Executors.newScheduledThreadPool(1)

scheduler.schedule(
  new Runnable { def run(): Unit = printRandomNumber }, 
  1, SECONDS
)
scheduler.shutdown()