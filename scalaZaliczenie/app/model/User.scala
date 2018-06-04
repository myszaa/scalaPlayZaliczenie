package model

import play.api.data.Form
import play.api.data.Forms._

case class User(username : String, password : String, email: String)

object User {


  val form = Form(mapping(
    "username" -> nonEmptyText(5),
    "password" -> nonEmptyText.verifying("5 znakow w tym 1 specjalny i 1 wielka litera",
      name => name.matches("^(?=.*[A-Z])(?=.*[$@$!%*?&^])[A-Za-z\\d$@$!%^*?&]{5,}") ),
    "email" -> email
  )
  (User.apply)(User.unapply))
}
