import scala.reflect.macros.Context
import scala.language.experimental.macros
import scala.annotation.StaticAnnotation

object slimCaseMacro {
  def impl(c: Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._
    import Flag._
    val result = {
      annottees.map(_.tree).toList match {
        case (cd : ClassDef) :: Nil =>
          val parents1 = cd.impl.parents filter {
            case tq"scala.Serializable" | tq"scala.Product" => false
            case _ => true
          }
          treeCopy.ClassDef(cd, cd.mods, cd.name, cd.tparams, treeCopy.Template(cd.impl, parents = parents1, cd.impl.self, cd.impl.body))
      }
    }
    c.Expr[Any](result)
  }
}

class slimCase extends StaticAnnotation {
  def macroTransform(annottees: Any*) = macro slimCaseMacro.impl
}
