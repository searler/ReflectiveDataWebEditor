
import com.google.gson.Gson

object ReactJsonGenerator {
  
  val gson = new Gson()

  def apply[T](d: T): String = s"""{ "data" : ${data(d)},"settings" : ${settings(d.getClass)}  }"""

  def data[T](d: T): String = gson.toJson(d)

  def settings(c: Class[_]): String = {
    def obj(c: Class[_]): String = c.getDeclaredFields.toList.filterNot(_.getType.isPrimitive).
      map { f =>
        val t = f.getType
        val n = f.getName
        if (t.isEnum) {
          val options = t.getDeclaredFields.filter { _.isEnumConstant }.map { _.getName }.toList.
            map { v => s""""$v"""" }.
            mkString(",")
          s""""$n" : { "type" : "select", "settings" : { "options" : [ $options] }}"""
        } else
          s""""$n" : { "type" : "object", "fields" : {  ${obj(t)} }}"""
      }.mkString(",")
    s"""{ "form" : true, "fields" : { ${obj(c)}}}"""
  }
}