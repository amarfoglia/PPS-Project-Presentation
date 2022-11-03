sealed trait AgeValidation extends Exception
case class NegativeAge(age: Int) extends AgeValidation
case class IllegalAge(age: Int) extends AgeValidation

def validate(age: Int): ZIO[Any, AgeValidation, Int] =
  if (age < 0) ZIO.fail(NegativeAge(age))
  else if (age < 18) ZIO.fail(IllegalAge(age))
  else ZIO.succeed(age)

val result: ZIO[Any, Nothing, Int] = validate(20).catchAll {
  case NegativeAge(age) => ZIO.debug(s"negative age: $age").as(-1)
  case IllegalAge(age) => ZIO.debug(s"illegal age: $age").as(-1)
}