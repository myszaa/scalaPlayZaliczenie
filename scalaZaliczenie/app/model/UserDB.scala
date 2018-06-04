package model

import com.avaje.ebean.Model
import javax.persistence.{Entity, Id}

@Entity
class UserDB extends Model{

  @Id
  var id: Long = 0
  var userName: String = ""
  var password: String = ""

}
