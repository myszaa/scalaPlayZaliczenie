package model

import play.api.data.Form
import play.api.data.Forms._

case class UserLogin(username : String, password : String)

object UserLogin {
  val form = Form(mapping(
    "username" -> nonEmptyText(5),
    "password" -> text
  )
  (UserLogin.apply)(UserLogin.unapply))
}
