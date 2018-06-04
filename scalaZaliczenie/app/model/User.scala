package model

import play.api.Logger
import play.api.data.Form
import play.api.data.Forms._

case class User(username : String, password : String, email: String)

object User {

  def uniqueUser(name: String) = {
    var userDB = new UserDB
    var userlist = userDB.findByName(name)
    Logger.info("Ilosc userow: " + userlist.length.toString)
    if(userlist.length > 0) false
    else true
  }


  val form = Form(mapping(
    "username" -> nonEmptyText.verifying("User exists", username => uniqueUser(username)),
    "password" -> nonEmptyText.verifying("5 znakow w tym 1 specjalny i 1 wielka litera",
      name => name.matches("^(?=.*[A-Z])(?=.*[$@$!%*?&^])[A-Za-z\\d$@$!%^*?&]{5,}") ),
    "email" -> email
  )
  (User.apply)(User.unapply))
}
