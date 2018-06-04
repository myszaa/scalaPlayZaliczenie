package model

import com.avaje.ebean.Model
import javax.persistence.{Entity, Id, ManyToOne, CascadeType}
import scala.collection.JavaConverters._

@Entity
class LuckyNumber extends Model{

  @Id
  var id: Long = 0
  var number: Int = 0;
  @ManyToOne(cascade= Array(CascadeType.ALL))
  var user:UserDB = _

  var find: Model.Finder[Long, LuckyNumber] = new Model.Finder[Long, LuckyNumber](classOf[Long], classOf[LuckyNumber])
  def all(): List[LuckyNumber] = find.all.asScala.toList

  def findByUserId(id: Long) : List[LuckyNumber]  = {
    find.where.eq("user_id", id).findList().asScala.toList
  }
}
