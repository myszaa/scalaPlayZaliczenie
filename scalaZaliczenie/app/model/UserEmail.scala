package model

import play.api.data.Form
import play.api.data.Forms._

case class UserEmail(email : String)

object UserEmail {
  val form = Form(mapping(
    "email" -> email
  )
  (UserEmail.apply)(UserEmail.unapply))
}
