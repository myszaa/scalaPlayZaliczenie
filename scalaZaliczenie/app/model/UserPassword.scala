package model

import play.api.data.Form
import play.api.data.Forms._

case class UserPassword(password : String)

object UserPassword {
  val form = Form(mapping(
    "password" -> nonEmptyText.verifying("5 znakow w tym 1 specjalny i 1 wielka litera",
      name => name.matches("^(?=.*[A-Z])(?=.*[$@$!%*?&^])[A-Za-z\\d$@$!%^*?&]{5,}") )
  )
  (UserPassword.apply)(UserPassword.unapply))
}
