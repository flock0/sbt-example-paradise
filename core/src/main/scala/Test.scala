@slimCase
case class Casey() extends Immutable

object Test extends App {
  def foo(t: Casey): Product = t
}