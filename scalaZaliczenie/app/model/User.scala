package model

import play.api.data.Form
import play.api.data.Forms._

case class User(username : String, password : String )

object User {
  val form = Form(mapping(
    "username" -> nonEmptyText(5),
    "password" -> text)
  (User.apply)(User.unapply))
}
