package Item

/**
 * A Good extends Item and represents a physical item worth 
 * some value
 */
class Good(g:GoodType,q:Double)  extends Item {
 
  //The type of Good
  val Goodtype:GoodType = g
  
  //The quantity of the good possessed
  var quantity:Double = q
}