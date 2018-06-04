package model

import com.avaje.ebean.Model
import javax.persistence.{Entity, Id, OneToMany, CascadeType}
import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

@Entity
class UserDB extends Model{

  @Id
  var id: Long = 0
  var userName: String = ""
  var password: String = ""
  var email: String = ""
  @OneToMany(cascade = Array(CascadeType.ALL),mappedBy="order")
  var lucky:ListBuffer[LuckyNumber] = new ListBuffer[LuckyNumber]

  var find: Model.Finder[Long, UserDB] = new Model.Finder[Long, UserDB](classOf[Long], classOf[UserDB])
  def all(): List[UserDB] = find.all.asScala.toList
  def findByName(name: String) : List[UserDB]  = {
    find.where.eq("user_name", name).findList().asScala.toList
  }
}
